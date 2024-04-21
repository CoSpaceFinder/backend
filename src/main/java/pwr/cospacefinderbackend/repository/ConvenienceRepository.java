package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.cospacefinderbackend.model.Convenience;

@Repository
public interface ConvenienceRepository extends JpaRepository<Convenience, Long> {
}
