package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.RoleDTO;
import pwr.cospacefinderbackend.exceptions.AlreadyExistsException;
import pwr.cospacefinderbackend.exceptions.CannotDeleteException;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.Role;
import pwr.cospacefinderbackend.repository.RoleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRole(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Role with id " + id + " does not exist")
        );
    }

    public Role addRole(RoleDTO role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new AlreadyExistsException("Role with name " + role.getName() + " already exists");
        }
        Role newRole = new Role();
        newRole.setName(role.getName());
        return roleRepository.save(newRole);
    }

    public Role updateRole(Long id, RoleDTO updatedRole) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            if (roleRepository.existsByName(updatedRole.getName()) && !role.getName().equals(updatedRole.getName())) {
                throw new AlreadyExistsException("Role with name " + updatedRole.getName() + " already exists");
            }
            role.setName(updatedRole.getName());
            return roleRepository.save(role);
        }
        throw new NotFoundException("Role with id " + id + " does not exist");
    }

    public Role deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            if ((role.getUsers() == null || role.getUsers().isEmpty())) {
                roleRepository.delete(role);
                return role;
            } else {
                throw new CannotDeleteException("Role with id " + id + " is used by some users");
            }
        } else {
            throw new NotFoundException("Role with id " + id + " does not exist");
        }
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Role with name " + name + " does not exist")
        );
    }
}
