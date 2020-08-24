package com.pyruz.samotech.uaa.model.entity;

import com.pyruz.samotech.shared.model.entity.BaseEntity;
import com.pyruz.samotech.shared.model.entity.Collections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "role",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"title", "caption"})
        }
)
@Where(clause = "is_deleted = false")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity<Integer> {

    @Column(name = "role_code", length = 20, nullable = false)
    private String roleCode;
    @Column(name = "title", length = 20, nullable = false)
    private String title;
    @Column(name = "caption", length = 20, nullable = false)
    private String caption;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_collections",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "collections_id")}
    )
    private List<Collections> collections;
}
