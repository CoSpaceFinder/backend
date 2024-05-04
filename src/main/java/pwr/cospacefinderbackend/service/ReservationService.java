package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.ReservationDTO;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.Reservation;
import pwr.cospacefinderbackend.model.Room;
import pwr.cospacefinderbackend.model.User;
import pwr.cospacefinderbackend.repository.ReservationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final RoomService roomService;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation with id " + id + " does not exist")
        );
    }

    public Reservation addReservation(ReservationDTO reservation) {
        Reservation newReservation = new Reservation();
        User user = userService.getUser(reservation.getUserId());
        Room room = roomService.getRoomById(reservation.getRoomId());
        newReservation.setUser(user);
        newReservation.setRoom(room);
        newReservation.setDesk(reservation.getDesk());
        newReservation.setDate(reservation.getDate());
        newReservation.setCode(1L);
        return reservationRepository.save(newReservation);
    }

    public Reservation updateReservation(Long id, ReservationDTO updatedReservation) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            User user = userService.getUser(updatedReservation.getUserId());
            Room room = roomService.getRoomById(updatedReservation.getRoomId());
            reservation.setUser(user);
            reservation.setRoom(room);
            reservation.setDesk(updatedReservation.getDesk());
            reservation.setDate(updatedReservation.getDate());
            reservation.setCode(1L);
            return reservationRepository.save(reservation);
        }
        throw new NotFoundException("Reservation with id " + id + " does not exist");
    }

    public Reservation deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            reservationRepository.delete(reservation);
            return reservation;
        } else {
            throw new NotFoundException("Reservation with id " + id + " does not exist");
        }
    }
}
