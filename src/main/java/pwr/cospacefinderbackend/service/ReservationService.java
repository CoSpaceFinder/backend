package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.ReservationDTO;
import pwr.cospacefinderbackend.exceptions.AlreadyExistsException;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.*;
import pwr.cospacefinderbackend.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final RoomService roomService;
    private final ReservationPartService reservationPartService;
    private final SpaceService spaceService;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation with id " + id + " does not exist")
        );
    }

    public Reservation addReservation(ReservationDTO reservation) {
        User user = userService.getUser(reservation.getUserId());
        Room room = roomService.getRoomById(reservation.getRoomId());

        validateDateRange(reservation.getStartDate(), reservation.getEndDate());
        validateDesk(reservation.getDesk(), room);
        checkIfThereAreOtherReservationsInTheSameTime(reservation);

        Reservation newReservation = new Reservation();
        newReservation.setUser(user);
        newReservation.setStartDate(reservation.getStartDate());
        newReservation.setEndDate(reservation.getEndDate());
        newReservation.setRoom(room);
        newReservation.setDesk(reservation.getDesk());
        newReservation.setPrice(calculatePrice(reservation.getStartDate(), reservation.getEndDate(), room));
        reservationRepository.save(newReservation);

        List<ReservationPart> reservationParts = divideReservationIntoParts(newReservation);
        try {
            reservationPartService.addReservationParts(reservationParts);
        } catch (Exception e) {
            reservationRepository.delete(newReservation);
            throw new AlreadyExistsException("Reservation for the room: " + reservation.getRoomId() +
                    ", desk: " + reservation.getDesk() + " and dates: " + reservation.getStartDate() +
                    " - " + reservation.getEndDate() + " already exists");
        }

        return newReservation;
    }

    public Reservation updateReservation(Long id, ReservationDTO updatedReservation) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation with id " + id + " does not exist")
        );

        User user = userService.getUser(updatedReservation.getUserId());
        Room room = roomService.getRoomById(updatedReservation.getRoomId());

        validateDateRange(updatedReservation.getStartDate(), updatedReservation.getEndDate());
        validateDesk(updatedReservation.getDesk(), room);

        reservation.setUser(user);
        reservation.setStartDate(updatedReservation.getStartDate());
        reservation.setEndDate(updatedReservation.getEndDate());
        reservation.setRoom(room);
        reservation.setDesk(updatedReservation.getDesk());
        reservation.setPrice(calculatePrice(updatedReservation.getStartDate(), updatedReservation.getEndDate(), room));

        List<ReservationPart> newReservationParts = divideReservationIntoParts(reservation);
        List<ReservationPart> reservationPartsToDelete = new ArrayList<>();
        reservationPartService.updateReservationParts(newReservationParts, reservationPartsToDelete, id);

        // add new reservation parts
        try {
            reservationPartService.addReservationParts(newReservationParts);
        } catch (Exception e) {
            throw new AlreadyExistsException("Reservation for the room: " + updatedReservation.getRoomId() +
                    ", desk: " + updatedReservation.getDesk() + " and dates: " + updatedReservation.getStartDate() +
                    " - " + updatedReservation.getEndDate() + " already exists");
        }

        // delete visit parts
        reservationPartService.deleteReservationParts(reservationPartsToDelete);

        return reservationRepository.save(reservation);
    }

    public Reservation deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation with id " + id + " does not exist")
        );

        reservationPartService.deleteReservationPartsByReservationId(id);
        reservationRepository.delete(reservation);
        return reservation;
    }

    private void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate))
            throw new IllegalArgumentException("Start date cannot be after end date");
        if (startDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Start date cannot be in the past");
    }

    private void validateDesk(Integer desk, Room room) {
        if (desk < 1 || desk > room.getCapacity())
            throw new IllegalArgumentException("Desk number must be between 1 and " + room.getCapacity());
    }

    private void checkIfThereAreOtherReservationsInTheSameTime(ReservationDTO reservation) {
        List<Reservation> reservations = reservationRepository.findAllByRoomIdAndDeskAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                reservation.getRoomId(), reservation.getDesk(), reservation.getEndDate(), reservation.getStartDate());
        if (!reservations.isEmpty()) {
            throw new AlreadyExistsException("Reservation for the room: " + reservation.getRoomId() +
                    ", desk: " + reservation.getDesk() + " and dates: " + reservation.getStartDate() +
                    " - " + reservation.getEndDate() + " already exists");
        }
    }

    private List<ReservationPart> divideReservationIntoParts(Reservation reservation){
        List<ReservationPart> reservationParts = new ArrayList<>();
        for (LocalDate date = reservation.getStartDate(); date.isBefore(reservation.getEndDate().plusDays(1));
             date = date.plusDays(1)) {
            if (spaceService.isOpenOnDayOfWeek(reservation.getRoom().getSpace(), date.getDayOfWeek().getValue())) {
                ReservationPart reservationPart = new ReservationPart();
                reservationPart.setReservation(reservation);
                reservationPart.setRoom(reservation.getRoom());
                reservationPart.setDesk(reservation.getDesk());
                reservationPart.setDate(date);
                reservationParts.add(reservationPart);
            }
        }
        return reservationParts;
    }

    private double calculatePrice(LocalDate startDate, LocalDate endDate, Room room) {
        long openDays = spaceService.calculateDaysWhenSpaceIsOpen(startDate, endDate, room.getSpace());
        return openDays * room.getPrice();
    }

    public List<Integer> getAvailableDesks(Long roomId, LocalDate startDate, LocalDate endDate) {
        validateDateRange(startDate, endDate);
        Room room = roomService.getRoomById(roomId);
        List<Integer> availableDesks = new ArrayList<>();

        List<Reservation> reservations = reservationRepository.findAllByRoomIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                roomId, endDate, startDate);

        Set<Integer> reservedDesks = reservations.stream()
                .map(Reservation::getDesk)
                .collect(Collectors.toSet());

        for (int i = 1; i <= room.getCapacity(); i++) {
            if (!reservedDesks.contains(i)) {
                availableDesks.add(i);
            }
        }

        return availableDesks;
    }
}
