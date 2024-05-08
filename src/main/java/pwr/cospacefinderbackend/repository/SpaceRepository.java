package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.cospacefinderbackend.model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    boolean existsByName(String name);
}
