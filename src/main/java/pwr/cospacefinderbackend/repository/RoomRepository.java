package pwr.cospacefinderbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.cospacefinderbackend.model.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findBySpaceId(Long spaceId);
    List<Room> findBySpaceIdAndName(Long spaceId, String name);
}
