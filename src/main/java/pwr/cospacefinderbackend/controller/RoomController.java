package pwr.cospacefinderbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pwr.cospacefinderbackend.dto.RoomDTO;
import pwr.cospacefinderbackend.model.Image;
import pwr.cospacefinderbackend.model.Room;
import pwr.cospacefinderbackend.service.RoomService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody RoomDTO room) {
        return new ResponseEntity<>(roomService.addRoom(room), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}/image", consumes = "multipart/form-data")
    @Operation(summary = "Add image to room", description = "Adds image to room with given id.")
    public ResponseEntity<Image> addImagesToRoom(@PathVariable Long id,
                                                 @RequestParam("image") MultipartFile image,
                                                 @RequestParam("caption") String caption) throws IOException {
        return ResponseEntity.ok(roomService.addImage(id, image, caption));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody RoomDTO room) {
        return new ResponseEntity<>(roomService.updateRoom(id, room), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Room> deleteRoom(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.deleteRoom(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/image/{imageId}")
    @Operation(summary = "Delete image from room", description = "Deletes image with given id from room with given id.")
    public ResponseEntity<Image> deleteImageFromRoom(@PathVariable Long id, @PathVariable Long imageId) {
        return ResponseEntity.ok(roomService.deleteImage(id, imageId));
    }
}
