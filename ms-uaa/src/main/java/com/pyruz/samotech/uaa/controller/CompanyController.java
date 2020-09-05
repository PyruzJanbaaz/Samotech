package com.pyruz.samotech.uaa.controller;

import com.pyruz.samotech.shared.model.domain.company.NewCompany;
import com.pyruz.samotech.shared.model.domain.company.UpdateCompany;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.uaa.service.company.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CompanyController {

    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/v1/company")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseDTO addCompany(@Valid @RequestBody NewCompany newCompany) {
        return companyService.addCompany(newCompany);
    }

    @PutMapping("/v1/company")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO editCompany(@Valid @RequestBody UpdateCompany updateCompany) {
        return companyService.editCompany(updateCompany);
    }

    @GetMapping("/v1/company/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getCompany(@PathVariable Integer companyId) {
        return companyService.getCompany(companyId);
    }

    @GetMapping("/v1/companies")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/v1/companies/{page}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getCompanies(@PathVariable Integer page) {
        return companyService.getCompanies(page);
    }

    @DeleteMapping("/v1/company")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO deleteCompany(@RequestParam Integer companyId) {
        return companyService.deleteCompany(companyId);
    }

}
