package pwr.cospacefinderbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwr.cospacefinderbackend.service.AvailabilityService;

@RestController
@AllArgsConstructor
@RequestMapping("/availability")
public class AvailabilityController {
    private final AvailabilityService availabilityService;
}
