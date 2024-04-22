package pwr.cospacefinderbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwr.cospacefinderbackend.service.SpaceService;

@RestController
@AllArgsConstructor
@RequestMapping("/space")
public class SpaceController {
    private final SpaceService spaceService;
}
