package com.pyruz.samotech.uaa.model.entity;

import com.pyruz.samotech.shared.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "member",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email","username"})
        }
)
@Where(clause = "is_deleted = false")
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity<Integer> {
    @Column(name = "member_code", length = 20, nullable = false)
    private String memberCode;
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;
    @Column(name = "username", length = 50, nullable = false)
    private String username;
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    @Email
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "avatar", length = 50)
    private String avatar;
    @Column(name = "address", length = 250)
    private String address;
    @Column(name = "birthday")
    private Timestamp birthday;
    @Column(name = "national_code", length = 10, nullable = false)
    private String nationalCode;
    @Column(name = "postal_code", length = 10, nullable = false)
    private String postalCode;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "member_role",
            joinColumns = {@JoinColumn(name = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "member_company",
            joinColumns = {@JoinColumn(name = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id")}

    )
    private Company company;


}
