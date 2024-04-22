package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.RoomTypeDTO;
import pwr.cospacefinderbackend.exceptions.AlreadyExistsException;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.RoomType;
import pwr.cospacefinderbackend.repository.RoomTypeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomType getRoomType(Long id) {
        return roomTypeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("RoomType with id " + id + " does not exist")
        );
    }

    public RoomType addRoomType(RoomTypeDTO RoomType) {
        if (roomTypeRepository.existsByName(RoomType.getName())) {
            throw new AlreadyExistsException("RoomType with name " + RoomType.getName() + " already exists");
        }
        RoomType newRoomType = new RoomType();
        newRoomType.setName(RoomType.getName());
        return roomTypeRepository.save(newRoomType);
    }

    public RoomType updateRoomType(Long id, RoomTypeDTO updatedRoomType) {
        RoomType RoomType = roomTypeRepository.findById(id).orElse(null);
        if (RoomType != null) {
            if (roomTypeRepository.existsByName(updatedRoomType.getName()) && !RoomType.getName().equals(updatedRoomType.getName())) {
                throw new AlreadyExistsException("RoomType with name " + updatedRoomType.getName() + " already exists");
            }
            RoomType.setName(updatedRoomType.getName());
            return roomTypeRepository.save(RoomType);
        }
        throw new NotFoundException("RoomType with id " + id + " does not exist");
    }

    public RoomType deleteRoomType(Long id) {
        RoomType RoomType = roomTypeRepository.findById(id).orElse(null);
        if (RoomType != null) {
            roomTypeRepository.delete(RoomType);
            return RoomType;
        } else {
            throw new NotFoundException("RoomType with id " + id + " does not exist");
        }
    }

    public RoomType getRoomTypeByName(String name) {
        return roomTypeRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("RoomType with name " + name + " does not exist")
        );
    }
}
