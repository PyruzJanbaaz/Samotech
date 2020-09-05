package com.pyruz.samotech.uaa.controller;

import com.pyruz.samotech.shared.model.domain.role.NewRole;
import com.pyruz.samotech.shared.model.domain.role.UpdateRole;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.uaa.service.role.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/v1/role")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseDTO addRole(@Valid @RequestBody NewRole newRole) {
        return roleService.addRole(newRole);
    }

    @PutMapping("/v1/role")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO editRole(@Valid @RequestBody UpdateRole updateRole) {
        return roleService.editRole(updateRole);
    }

    @GetMapping("/v1/role/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getRole(@PathVariable Integer roleId) {
        return roleService.getRole(roleId);
    }

    @GetMapping("/v1/roles")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/v1/roles/{page}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getRoles(@PathVariable Integer page) {
        return roleService.getRoles(page);
    }

    @DeleteMapping("/v1/role")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO deleteRole(@RequestParam Integer roleId) {
        return roleService.deleteRole(roleId);
    }
}
