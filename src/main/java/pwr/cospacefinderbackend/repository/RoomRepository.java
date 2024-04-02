package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.cospacefinderbackend.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
