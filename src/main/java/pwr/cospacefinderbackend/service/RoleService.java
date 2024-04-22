package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.repository.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
}
