package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.cospacefinderbackend.model.Availability;
import pwr.cospacefinderbackend.model.Space;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    boolean existsBySpaceAndDayOfWeek(Space space, int dayOfWeek);
}
