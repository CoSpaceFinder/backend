package pwr.cospacefinderbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.cospacefinderbackend.dto.RoleDTO;
import pwr.cospacefinderbackend.model.Role;
import pwr.cospacefinderbackend.service.RoleService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    @Operation(summary = "Get all roles", description = "Returns all roles from database.")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get role by id", description = "Returns role with given id.")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRole(id));
    }

    @PostMapping
    @Operation(summary = "Add role", description = "Adds role to database.")
    public ResponseEntity<Role> addRole(@RequestBody RoleDTO role) {
        return new ResponseEntity<>(roleService.addRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update role", description = "Updates role with given id.")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleDTO updatedRole) {
        return ResponseEntity.ok(roleService.updateRole(id, updatedRole));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete role", description = "Deletes role with given id.")
    public ResponseEntity<Role> deleteRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.deleteRole(id));
    }
}
