package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.cospacefinderbackend.model.Convenience;

import java.util.Optional;

@Repository
public interface ConvenienceRepository extends JpaRepository<Convenience, Long> {
    boolean existsByName(String name);
    Optional<Convenience> findByName(String name);
}
