package pwr.cospacefinderbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwr.cospacefinderbackend.service.ConvenienceService;

@RestController
@AllArgsConstructor
@RequestMapping("/convenience")
public class ConvenienceController {
    private final ConvenienceService convenienceService;
}
