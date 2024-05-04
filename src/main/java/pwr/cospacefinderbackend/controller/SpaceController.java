package pwr.cospacefinderbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.cospacefinderbackend.dto.SpaceDTO;
import pwr.cospacefinderbackend.model.Space;
import pwr.cospacefinderbackend.service.SpaceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/space")
public class SpaceController {
    private final SpaceService spaceService;

    @GetMapping
    @Operation(summary = "Get all spaces", description = "Returns all spaces from database.")
    public ResponseEntity<List<Space>> getAllSpaces() {
        return ResponseEntity.ok(spaceService.getAllSpaces());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get space by id", description = "Returns space with given id.")
    public ResponseEntity<Space> getSpaceById(@PathVariable Long id) {
        return ResponseEntity.ok(spaceService.getSpace(id));
    }

    @PostMapping
    @Operation(summary = "Add space", description = "Adds space to database.")
    public ResponseEntity<Space> addSpace(@RequestBody SpaceDTO space) {
        return new ResponseEntity<>(spaceService.addSpace(space), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update space", description = "Updates space with given id.")
    public ResponseEntity<Space> updateSpace(@PathVariable Long id, @RequestBody SpaceDTO updatedSpace) {
        return ResponseEntity.ok(spaceService.updateSpace(id, updatedSpace));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete space", description = "Deletes space with given id.")
    public ResponseEntity<Space> deleteSpace(@PathVariable Long id) {
        return ResponseEntity.ok(spaceService.deleteSpace(id));
    }
}
