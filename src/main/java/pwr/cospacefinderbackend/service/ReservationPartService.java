package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.model.ReservationPart;
import pwr.cospacefinderbackend.repository.ReservationPartRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationPartService {
    private final ReservationPartRepository reservationPartRepository;

    public List<ReservationPart> getAllReservationPartsByReservationId(long reservationId) {
        return reservationPartRepository.findAllByReservationId(reservationId);
    }

    public List<ReservationPart> addReservationParts(List<ReservationPart> reservationParts){
        return reservationPartRepository.saveAllAndFlush(reservationParts);
    }

    public void updateReservationParts(List<ReservationPart> newReservationParts,
                                       List<ReservationPart> reservationPartsToDelete, long reservationId){
        List<ReservationPart> reservationParts = getAllReservationPartsByReservationId(reservationId);

        for (ReservationPart reservationPart : reservationParts) {
            boolean isReservationPartToBeDeleted = true;
            for (ReservationPart newReservationPart : newReservationParts) {
                if (reservationPart.getRoom().getId().equals(newReservationPart.getRoom().getId())
                        && reservationPart.getDesk().equals(newReservationPart.getDesk())
                        && reservationPart.getDate().equals(newReservationPart.getDate())) {

                    newReservationParts.remove(newReservationPart);
                    isReservationPartToBeDeleted = false;
                    break;
                }
            }
            if (isReservationPartToBeDeleted)
                reservationPartsToDelete.add(reservationPart);
        }
    }

    public void deleteReservationPartsByReservationId(long reservationId){
        List<ReservationPart> reservationParts = reservationPartRepository.findAllByReservationId(reservationId);
        reservationPartRepository.deleteAll(reservationParts);
    }

    public void deleteReservationParts(List<ReservationPart> reservationPartsToDelete){
        reservationPartRepository.deleteAll(reservationPartsToDelete);
    }
}
