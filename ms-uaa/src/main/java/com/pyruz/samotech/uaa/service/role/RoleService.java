package com.pyruz.samotech.uaa.service.role;

import com.pyruz.samotech.shared.handler.exception.ServiceException;
import com.pyruz.samotech.shared.model.domain.role.NewRole;
import com.pyruz.samotech.shared.model.domain.role.UpdateRole;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.shared.model.dto.base.MetaDTO;
import com.pyruz.samotech.shared.model.dto.base.PagerDTO;
import com.pyruz.samotech.shared.utility.ApplicationProperties;
import com.pyruz.samotech.shared.utility.ApplicationUtilities;
import com.pyruz.samotech.uaa.model.entity.Role;
import com.pyruz.samotech.uaa.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {


    final RoleRepository roleRepository;
    final ApplicationProperties applicationProperties;

    public RoleService(RoleRepository roleRepository, ApplicationProperties applicationProperties) {
        this.roleRepository = roleRepository;
        this.applicationProperties = applicationProperties;
    }

    public BaseDTO addRole(NewRole newRole) {
        roleRepository.save(
                Role.builder()
                        .title(newRole.getTitle())
                        .caption(newRole.getCaption())
                        .roleCode(UUID.randomUUID().toString())
                        .build()
        );
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();
    }

    public BaseDTO editRole(UpdateRole updateRole) {
        Role role = roleRepository.findById(updateRole.getId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        role.setTitle(updateRole.getTitle());
        role.setCaption(updateRole.getCaption());
        role.setIsActive(updateRole.getIsActive());
        roleRepository.save(role);
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();

    }

    public BaseDTO<Role> getRole(Integer id) {
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), roleRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
    }

    public BaseDTO deleteRole(Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        role.setIsDeleted(true);
        roleRepository.save(role);
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();

    }

    public BaseDTO<List<Role>> getAllRoles() {
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), roleRepository.findAll());
    }

    public BaseDTO<PagerDTO<Role>> getRoles(Integer page) {
        Pageable pageable = ApplicationUtilities.getInstance().pageable(page, applicationProperties);
        Page<Role> companies = roleRepository.findAll(pageable);
        PagerDTO<Role> companyPagerDTO = new PagerDTO<>(companies);
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), companyPagerDTO);
    }

}
