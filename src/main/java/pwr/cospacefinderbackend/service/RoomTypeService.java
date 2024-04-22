package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.repository.RoomTypeRepository;

@Service
@AllArgsConstructor
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
}
