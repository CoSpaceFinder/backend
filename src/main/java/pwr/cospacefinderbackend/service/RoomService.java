package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pwr.cospacefinderbackend.dto.RoomDTO;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.Image;
import pwr.cospacefinderbackend.model.Room;
import pwr.cospacefinderbackend.model.RoomType;
import pwr.cospacefinderbackend.model.Space;
import pwr.cospacefinderbackend.repository.RoomRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeService roomTypeService;
    private final SpaceService spaceService;
    private final ImageService imageService;

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
        newRoom.setImages(new ArrayList<>());
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

    @Transactional
    public Room deleteRoom(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            // Delete the images associated with the room
            List<Image> images = room.getImages();
            if (images != null) {
                for (Image image : images) {
                    imageService.deleteImage(image.getId());
                }
                room.setImages(null);
            }
            roomRepository.delete(room);
            return room;
        } else {
            throw new NotFoundException("Room with id " + id + " does not exist");
        }
    }

    public Image addImage(Long id, MultipartFile image, String caption) throws IOException {
        Room room = roomRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Room with id " + id + " does not exist")
        );

        if (image == null || caption == null) {
            throw new IllegalArgumentException("Image and caption must be present");
        }

        Image newImage = imageService.addImage(image, caption);
        room.getImages().add(newImage);
        roomRepository.save(room);
        return newImage;
    }

    public Image deleteImage(Long id, Long imageId) {
        Room room = roomRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Room with id " + id + " does not exist")
        );

        Image image = imageService.getImage(imageId);
        if (image == null || !room.getImages().contains(image)) {
            throw new NotFoundException("Image with id " + imageId + " does not exist in the room");
        }
        room.getImages().remove(image);
        imageService.deleteImage(imageId);
        roomRepository.save(room);
        return image;
    }



}
