package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pwr.cospacefinderbackend.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
