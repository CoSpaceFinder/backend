package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.repository.SpaceRepository;

@Service
@AllArgsConstructor
public class SpaceService {
    private final SpaceRepository spaceRepository;

}
