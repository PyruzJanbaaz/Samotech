package com.pyruz.samotech.uaa.service.company;

import com.pyruz.samotech.shared.handler.exception.ServiceException;
import com.pyruz.samotech.shared.model.domain.company.NewCompany;
import com.pyruz.samotech.shared.model.domain.company.UpdateCompany;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.shared.model.dto.base.MetaDTO;
import com.pyruz.samotech.shared.model.dto.base.PagerDTO;
import com.pyruz.samotech.shared.utility.ApplicationProperties;
import com.pyruz.samotech.shared.utility.ApplicationUtilities;
import com.pyruz.samotech.uaa.model.entity.Company;
import com.pyruz.samotech.uaa.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Service
public class CompanyService {

    final CompanyRepository companyRepository;
    final ApplicationProperties applicationProperties;

    public CompanyService(CompanyRepository companyRepository, ApplicationProperties applicationProperties) {
        this.companyRepository = companyRepository;
        this.applicationProperties = applicationProperties;
    }

    public BaseDTO addCompany(NewCompany newCompany) {
        companyRepository.save(Company.builder()
                .title(newCompany.getTitle())
                .address(newCompany.getAddress())
                .economicCode(newCompany.getEconomicCode())
                .email(newCompany.getEmail())
                .fax(newCompany.getFax())
                .phoneNumber(newCompany.getPhone())
                .logo(newCompany.getLogo())
                .webSiteUrl(newCompany.getWebSiteURL())
                .postalCode(newCompany.getPostalCode())
                .companyCode(UUID.randomUUID().toString())
                .build()
        );
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();
    }

    public BaseDTO editCompany(UpdateCompany updateCompany) {
        Company company = companyRepository.findById(updateCompany.getId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        company.setTitle(updateCompany.getTitle());
        company.setAddress(updateCompany.getAddress());
        company.setEconomicCode(updateCompany.getEconomicCode());
        company.setEmail(updateCompany.getEmail());
        company.setFax(updateCompany.getFax());
        company.setPhoneNumber(updateCompany.getPhone());
        company.setLogo(updateCompany.getLogo());
        company.setWebSiteUrl(updateCompany.getWebSiteURL());
        company.setPostalCode(updateCompany.getPostalCode());
        company.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        company.setIsActive(updateCompany.getIsActive());
        companyRepository.save(company);
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();
    }

    public BaseDTO<Company> getCompany(Integer id) {
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), companyRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
    }

    public BaseDTO deleteCompany(Integer id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        company.setIsDeleted(true);
        companyRepository.save(company);
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();
    }


    public BaseDTO<List<Company>> getAllCompanies() {
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), companyRepository.findAll());
    }

    public BaseDTO<PagerDTO<Company>> getCompanies(Integer page) {
        Pageable pageable = ApplicationUtilities.getInstance().pageable(page, applicationProperties);
        Page<Company> companies = companyRepository.findAll(pageable);
        PagerDTO<Company> companyPagerDTO = new PagerDTO<>(companies);
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), companyPagerDTO);
    }


}
