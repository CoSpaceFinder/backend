package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.cospacefinderbackend.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByMail(String mail);
    Optional<User> findByMail(String mail);
}
