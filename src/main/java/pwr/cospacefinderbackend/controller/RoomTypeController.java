package pwr.cospacefinderbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwr.cospacefinderbackend.service.RoomTypeService;

@RestController
@AllArgsConstructor
@RequestMapping("/room-type")
public class RoomTypeController {
    private final RoomTypeService roomTypeService;
}
