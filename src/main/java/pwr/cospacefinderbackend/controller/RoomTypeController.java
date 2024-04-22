package pwr.cospacefinderbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.cospacefinderbackend.dto.RoomTypeDTO;
import pwr.cospacefinderbackend.model.RoomType;
import pwr.cospacefinderbackend.service.RoomTypeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/room-type")
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    @GetMapping
    @Operation(summary = "Get all room types", description = "Returns all room types from database.")
    public ResponseEntity<List<RoomType>> getAllRoomTypes() {
        return ResponseEntity.ok(roomTypeService.getAllRoomTypes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get room type by id", description = "Returns room type with given id.")
    public ResponseEntity<RoomType> getRoomTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(roomTypeService.getRoomType(id));
    }

    @PostMapping
    @Operation(summary = "Add room type", description = "Adds room type to database.")
    public ResponseEntity<RoomType> addRoomType(@RequestBody RoomTypeDTO roomType) {
        return new ResponseEntity<>(roomTypeService.addRoomType(roomType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update room type", description = "Updates room type with given id.")
    public ResponseEntity<RoomType> updateRoomType(@PathVariable Long id, @RequestBody RoomTypeDTO updatedRoomType) {
        return ResponseEntity.ok(roomTypeService.updateRoomType(id, updatedRoomType));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete room type", description = "Deletes room type with given id.")
    public ResponseEntity<RoomType> deleteRoomType(@PathVariable Long id) {
        return ResponseEntity.ok(roomTypeService.deleteRoomType(id));
    }
}
