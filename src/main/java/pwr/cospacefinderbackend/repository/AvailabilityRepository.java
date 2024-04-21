package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.cospacefinderbackend.model.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
}
