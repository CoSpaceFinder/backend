package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pwr.cospacefinderbackend.model.Space;

public interface SpaceRepository extends JpaRepository<Space, Long> {
}
