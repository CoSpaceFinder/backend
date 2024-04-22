package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.repository.ConvenienceRepository;

@Service
@AllArgsConstructor
public class ConvenienceService {
    private final ConvenienceRepository convenienceRepository;
}
