package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pwr.cospacefinderbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
