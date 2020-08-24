package com.pyruz.samotech.uaa.repository;

import com.pyruz.samotech.uaa.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {

    List<Role> findRoleByIdIn(List<Integer> roleIds);
}
