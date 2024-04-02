package pwr.cospacefinderbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.cospacefinderbackend.dto.RoomDTO;
import pwr.cospacefinderbackend.model.Room;
import pwr.cospacefinderbackend.service.RoomService;

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

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody RoomDTO room) {
        return new ResponseEntity<>(roomService.updateRoom(id, room), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Room> deleteRoom(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.deleteRoom(id), HttpStatus.OK);
    }


}
