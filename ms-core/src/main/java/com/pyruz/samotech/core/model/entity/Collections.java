package com.pyruz.samotech.core.model.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "collections",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title", "caption"}))
@Where(clause = "is_deleted = false")
public class Collections extends BaseEntity<Integer> implements Serializable {

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "collection_code", length = 50, nullable = false)
    private String collectionCode;

    @Column(name = "caption", length = 50, nullable = false)
    private String caption;

    @ToString.Exclude
    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> projects;

}
