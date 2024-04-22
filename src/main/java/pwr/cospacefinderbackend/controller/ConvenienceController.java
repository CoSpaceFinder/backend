package pwr.cospacefinderbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.cospacefinderbackend.dto.ConvenienceDTO;
import pwr.cospacefinderbackend.model.Convenience;
import pwr.cospacefinderbackend.service.ConvenienceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/convenience")
public class ConvenienceController {
    private final ConvenienceService convenienceService;

    @GetMapping
    @Operation(summary = "Get all conveniences", description = "Returns all conveniences from database.")
    public ResponseEntity<List<Convenience>> getAllConveniences() {
        return ResponseEntity.ok(convenienceService.getAllConveniences());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get convenience by id", description = "Returns  convenience with given id.")
    public ResponseEntity<Convenience> getConvenienceById(@PathVariable Long id) {
        return ResponseEntity.ok(convenienceService.getConvenience(id));
    }

    @PostMapping
    @Operation(summary = "Add convenience", description = "Adds convenience to database.")
    public ResponseEntity<Convenience> addConvenience(@RequestBody ConvenienceDTO convenience) {
        return new ResponseEntity<>(convenienceService.addConvenience(convenience), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update convenience", description = "Updates convenience with given id.")
    public ResponseEntity<Convenience> updateConvenience(@PathVariable Long id, @RequestBody ConvenienceDTO updatedConvenience) {
        return ResponseEntity.ok(convenienceService.updateConvenience(id, updatedConvenience));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete convenience", description = "Deletes convenience with given id.")
    public ResponseEntity<Convenience> deleteConvenience(@PathVariable Long id) {
        return ResponseEntity.ok(convenienceService.deleteConvenience(id));
    }
}
