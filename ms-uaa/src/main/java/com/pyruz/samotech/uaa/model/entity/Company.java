package com.pyruz.samotech.uaa.model.entity;

import com.pyruz.samotech.shared.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@Table(name = "company")
@Where(clause = "is_deleted = false")
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity<Integer> {

    @Column(name = "company_code", length = 50, nullable = false)
    private String companyCode;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Email
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
    @Size(max = 500)
    @Column(name = "address", length = 500)
    private String address;
    @Column(name = "web_site_url", length = 100)
    private String webSiteUrl;
    @Column(name = "logo", length = 50)
    private String logo;
    @Column(name = "economic_code", length = 20)
    private String economicCode;
    @Column(name = "postal_code", length = 10)
    private String postalCode;
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;
    @Column(name = "fax", length = 15)
    private String fax;

}
