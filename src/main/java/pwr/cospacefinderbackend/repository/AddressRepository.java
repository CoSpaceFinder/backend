package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.cospacefinderbackend.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
