package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.RoomDTO;
import pwr.cospacefinderbackend.model.Room;
import pwr.cospacefinderbackend.repository.RoomRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Room with id " + id + " does not exist")
        );
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room addRoom(RoomDTO room) {
        Room newRoom = new Room();
        newRoom.setSpace(room.getSpace());
        newRoom.setName(room.getName());
        newRoom.setRoomType(room.getRoomType());
        newRoom.setCapacity(room.getCapacity());
        return roomRepository.save(newRoom);
    }

    public Room updateRoom(Long id, RoomDTO room) {
        Room updatedRoom = roomRepository.findById(id).orElse(null);
        if (updatedRoom != null) {
            updatedRoom.setSpace(room.getSpace());
            updatedRoom.setName(room.getName());
            updatedRoom.setRoomType(room.getRoomType());
            updatedRoom.setCapacity(room.getCapacity());
            return roomRepository.save(updatedRoom);
        } else {
            throw new NoSuchElementException("Room with id " + id + " does not exist");
        }
    }

    public Room deleteRoom(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            roomRepository.delete(room);
            return room;
        } else {
            throw new NoSuchElementException("Room with id " + id + " does not exist");
        }
    }


}
