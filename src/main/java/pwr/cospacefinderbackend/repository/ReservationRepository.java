package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pwr.cospacefinderbackend.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByRoomIdAndDeskAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long id, Integer desk,
                                                                                                LocalDate endDate,
                                                                                                LocalDate startDate);
    List<Reservation> findAllByRoomIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long roomId, LocalDate endDate,
                                                                                         LocalDate startDate);
}
