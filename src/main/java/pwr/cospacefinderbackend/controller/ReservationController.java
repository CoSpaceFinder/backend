package pwr.cospacefinderbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwr.cospacefinderbackend.service.ReservationService;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
}
