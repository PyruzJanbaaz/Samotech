package com.pyruz.samotech.uaa.repository;

import com.pyruz.samotech.uaa.model.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository  extends JpaRepository<Company, Integer> {

    Page<Company> findAll(Pageable pageable);

}
