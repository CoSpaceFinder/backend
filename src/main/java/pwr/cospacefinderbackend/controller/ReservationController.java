package pwr.cospacefinderbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.cospacefinderbackend.dto.ReservationDTO;
import pwr.cospacefinderbackend.model.Reservation;
import pwr.cospacefinderbackend.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    @Operation(summary = "Get all reservations", description = "Returns all reservations from database.")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get reservation by id", description = "Returns reservation with given id.")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservation(id));
    }

    @GetMapping("/available-desks/{roomId}/{startDate}/{endDate}")
    @Operation(summary = "Get available desks", description = "Returns available desks for given room and date range.")
    public ResponseEntity<List<Integer>> getAvailableDesks(@PathVariable Long roomId,
                                                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate,
                                                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return ResponseEntity.ok(reservationService.getAvailableDesks(roomId, startDate, endDate));
    }

    @PostMapping
    @Operation(summary = "Add reservation", description = "Adds reservation to database.")
    public ResponseEntity<Reservation> addReservation(@RequestBody ReservationDTO reservation) {
        return new ResponseEntity<>(reservationService.addReservation(reservation), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update reservation", description = "Updates reservation with given id.")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id,
                                                         @RequestBody ReservationDTO updatedReservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, updatedReservation));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete reservation", description = "Deletes reservation with given id.")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.deleteReservation(id));
    }
}
