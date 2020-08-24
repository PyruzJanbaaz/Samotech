package com.pyruz.samotech.uaa.controller;

import com.pyruz.samotech.shared.model.domain.role.NewRole;
import com.pyruz.samotech.shared.model.domain.role.UpdateRole;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.uaa.service.role.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseDTO> addRole(@Valid @RequestBody NewRole newRole) {
        return new ResponseEntity<>(roleService.addRole(newRole), HttpStatus.CREATED);
    }

    @PutMapping("/v1/role")
    public ResponseEntity<BaseDTO> editRole(@Valid @RequestBody UpdateRole updateRole) {
        return new ResponseEntity<>(roleService.editRole(updateRole), HttpStatus.OK);
    }

    @GetMapping("/v1/role/{roleId}")
    public ResponseEntity<BaseDTO> getRole(@PathVariable Integer roleId) {
        return new ResponseEntity<>(roleService.getRole(roleId), HttpStatus.OK);
    }

    @GetMapping("/v1/roles")
    public ResponseEntity<BaseDTO> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/v1/roles/{page}")
    public ResponseEntity<BaseDTO> getRoles(@PathVariable Integer page) {
        return new ResponseEntity<>(roleService.getRoles(page), HttpStatus.OK);
    }

    @DeleteMapping("/v1/role")
    public ResponseEntity<BaseDTO> deleteRole(@RequestParam Integer roleId) {
        return new ResponseEntity<>(roleService.deleteRole(roleId), HttpStatus.OK);
    }
}
