package com.pyruz.samotech.uaa.controller;

import com.pyruz.samotech.shared.model.domain.company.NewCompany;
import com.pyruz.samotech.shared.model.domain.company.UpdateCompany;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.uaa.service.company.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CompanyController  {

    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/v1/company")
    public ResponseEntity<BaseDTO> addCompany(@Valid @RequestBody NewCompany newCompany) {
        return new ResponseEntity<>(companyService.addCompany(newCompany), HttpStatus.CREATED);
    }

    @PutMapping("/v1/company")
    public ResponseEntity<BaseDTO> editCompany(@Valid @RequestBody UpdateCompany updateCompany) {
        return new ResponseEntity<>(companyService.editCompany(updateCompany), HttpStatus.OK);
    }

    @GetMapping("/v1/company/{companyId}")
    public ResponseEntity<BaseDTO> getCompany(@PathVariable Integer companyId) {
        return new ResponseEntity<>(companyService.getCompany(companyId), HttpStatus.OK);
    }

    @GetMapping("/v1/companies")
    public ResponseEntity<BaseDTO> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/v1/companies/{page}")
    public ResponseEntity<BaseDTO> getCompanies(@PathVariable Integer page) {
        return new ResponseEntity<>(companyService.getCompanies(page), HttpStatus.OK);
    }

    @DeleteMapping("/v1/company")
    public ResponseEntity<BaseDTO> deleteCompany(@RequestParam Integer companyId) {
        return new ResponseEntity<>(companyService.deleteCompany(companyId), HttpStatus.OK);
    }

}
