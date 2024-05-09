package pwr.cospacefinderbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pwr.cospacefinderbackend.dto.SpaceDTO;
import pwr.cospacefinderbackend.model.Image;
import pwr.cospacefinderbackend.model.Space;
import pwr.cospacefinderbackend.service.SpaceService;

import java.io.IOException;
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

    @PostMapping(value = "/{id}/images", consumes = "multipart/form-data")
    @Operation(summary = "Add images to space", description = "Adds images to space with given id.")
    public ResponseEntity<Image> addImagesToSpace(@PathVariable Long id,
                                                  @RequestParam("image") MultipartFile image,
                                                  @RequestParam("caption") String caption) throws IOException {
        return ResponseEntity.ok(spaceService.addImage(id, image, caption));
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

    @DeleteMapping("/{id}/images/{imageId}")
    @Operation(summary = "Delete image from space", description = "Deletes image with given id from space with given id.")
    public ResponseEntity<Image> deleteImageFromSpace(@PathVariable Long id, @PathVariable Long imageId) {
        return ResponseEntity.ok(spaceService.deleteImage(id, imageId));
    }
}
