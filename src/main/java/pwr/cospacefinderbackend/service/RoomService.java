package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.RoomDTO;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.Room;
import pwr.cospacefinderbackend.model.RoomType;
import pwr.cospacefinderbackend.model.Space;
import pwr.cospacefinderbackend.repository.RoomRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeService roomTypeService;
    private final SpaceService spaceService;

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Room with id " + id + " does not exist")
        );
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room addRoom(RoomDTO room) {
        Room newRoom = new Room();
        Space space = spaceService.getSpace(room.getSpaceId());
        newRoom.setSpace(space);
        newRoom.setName(room.getName());
        RoomType roomType = roomTypeService.getRoomType(room.getRoomTypeId());
        newRoom.setRoomType(roomType);
        newRoom.setCapacity(room.getCapacity());
        newRoom.setFloor(room.getFloor());
        newRoom.setPrice(room.getPrice());
        return roomRepository.save(newRoom);
    }

    public Room updateRoom(Long id, RoomDTO room) {
        Room updatedRoom = roomRepository.findById(id).orElse(null);
        if (updatedRoom != null) {
            Space space = spaceService.getSpace(room.getSpaceId());
            updatedRoom.setSpace(space);
            updatedRoom.setName(room.getName());
            RoomType roomType = roomTypeService.getRoomType(room.getRoomTypeId());
            updatedRoom.setRoomType(roomType);
            updatedRoom.setCapacity(room.getCapacity());
            updatedRoom.setFloor(room.getFloor());
            updatedRoom.setPrice(room.getPrice());
            return roomRepository.save(updatedRoom);
        } else {
            throw new NotFoundException("Room with id " + id + " does not exist");
        }
    }

    public Room deleteRoom(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            roomRepository.delete(room);
            return room;
        } else {
            throw new NotFoundException("Room with id " + id + " does not exist");
        }
    }


}
