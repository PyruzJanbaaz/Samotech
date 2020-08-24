package com.pyruz.samotech.uaa.service.member;

import com.pyruz.samotech.shared.handler.exception.ServiceException;
import com.pyruz.samotech.shared.model.domain.member.NewMember;
import com.pyruz.samotech.shared.model.domain.member.UpdateMember;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.shared.model.dto.base.MetaDTO;
import com.pyruz.samotech.shared.model.dto.base.PagerDTO;
import com.pyruz.samotech.shared.utility.ApplicationProperties;
import com.pyruz.samotech.shared.utility.ApplicationUtilities;
import com.pyruz.samotech.uaa.model.entity.Member;
import com.pyruz.samotech.uaa.repository.CompanyRepository;
import com.pyruz.samotech.uaa.repository.MemberRepository;
import com.pyruz.samotech.uaa.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
public class MemberService {


    final CompanyRepository companyRepository;
    final MemberRepository memberRepository;
    final RoleRepository roleRepository;
    final ApplicationProperties applicationProperties;

    public MemberService(CompanyRepository companyRepository, MemberRepository memberRepository, RoleRepository roleRepository, ApplicationProperties applicationProperties) {
        this.companyRepository = companyRepository;
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.applicationProperties = applicationProperties;
    }

    public BaseDTO addMember(NewMember newMember) {
        memberRepository.save(
                Member.builder()
                        .firstName(newMember.getFirstName())
                        .lastName(newMember.getLastName())
                        .address(newMember.getAddress())
                        .avatar(newMember.getAvatar())
                        .birthday((Timestamp) new Date())
                        .email(newMember.getEmail())
                        .nationalCode(newMember.getNationalCode())
                        .username(newMember.getUsername())
                        .password(MD5Password(newMember.getPassword()))
                        .postalCode(newMember.getPostalCode())
                        .memberCode(UUID.randomUUID().toString())
                        .company(
                                companyRepository.findById(newMember.getCompanyId()).orElseThrow(
                                        () -> new ServiceException(
                                                applicationProperties.getCode("not-found-code"),
                                                applicationProperties.getProperty("not-found-text"),
                                                HttpStatus.NOT_FOUND
                                        )
                                ))
                        .roles(roleRepository.findRoleByIdIn(newMember.getRoles()))
                        .build()
        );
        return new BaseDTO(MetaDTO.getInstance(applicationProperties));
    }

    public BaseDTO editMember(UpdateMember updateMember) {
        Member member = memberRepository.findById(updateMember.getId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        member.setFirstName(updateMember.getFirstName());
        member.setLastName(updateMember.getLastName());
        member.setAddress(updateMember.getAddress());
        member.setAvatar(updateMember.getAvatar());
        member.setBirthday((Timestamp) new Date());
        member.setCompany(companyRepository.findById(updateMember.getCompanyId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
        member.setRoles(roleRepository.findRoleByIdIn(updateMember.getRoles()));
        member.setEmail(updateMember.getEmail());
        member.setPostalCode(updateMember.getPostalCode());
        memberRepository.save(member);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties));
    }

    public BaseDTO getMember(Integer id) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), memberRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
    }

    public BaseDTO deleteMember(Integer id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        member.setIsDeleted(true);
        memberRepository.save(member);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties));
    }

    public BaseDTO getMembers(Integer page) {
        Pageable pageable = ApplicationUtilities.getInstance().pageable(page, applicationProperties);
        Page<Member> members = memberRepository.findAll(pageable);
        PagerDTO<Member> memberPagerDTO = new PagerDTO<>(members);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), memberPagerDTO);
    }

    public BaseDTO getAllMembers() {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), memberRepository.findAll());
    }

    private static String MD5Password(String plainPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashedPassword = new StringBuilder();
            for (byte b : hashInBytes) {
                hashedPassword.append(String.format("%02x", b));
            }
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new ServiceException();
    }
}
