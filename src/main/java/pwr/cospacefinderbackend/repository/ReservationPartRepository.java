package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.cospacefinderbackend.model.ReservationPart;

import java.util.List;

@Repository
public interface ReservationPartRepository extends JpaRepository<ReservationPart, Long> {
    List<ReservationPart> findAllByReservationId(Long reservationId);
}
