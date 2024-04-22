package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.repository.AvailabilityRepository;

@Service
@AllArgsConstructor
public class AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
}
