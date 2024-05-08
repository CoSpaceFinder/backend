package pwr.cospacefinderbackend.controller;

import com.azure.storage.blob.models.BlobHttpHeaders;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pwr.cospacefinderbackend.model.Image;
import pwr.cospacefinderbackend.service.ImageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    @Operation(summary = "Get all images", description = "Returns all images from database.")
    public ResponseEntity<List<Image>> getAllImages() {
        return ResponseEntity.ok(imageService.getAllImages());
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Image> addImage(@RequestParam("image") MultipartFile image,
                                          @RequestParam("caption") String caption) throws IOException {
        return new ResponseEntity<>(imageService.addImage(image, caption), HttpStatus.CREATED);
    }
}
