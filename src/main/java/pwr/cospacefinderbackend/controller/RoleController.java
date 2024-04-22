package pwr.cospacefinderbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwr.cospacefinderbackend.service.RoleService;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
}
