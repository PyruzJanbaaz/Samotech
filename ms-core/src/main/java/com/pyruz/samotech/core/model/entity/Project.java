package com.pyruz.samotech.core.model.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "project",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title", "caption"}))
@Where(clause = "is_deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Project extends BaseEntity<Integer> implements Serializable {

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 100)
    private String caption;

    @NotNull
    @Size(max = 50)
    private String backgroundImage;

    @NotNull
    @Size(max = 6)
    private String backgroundColor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_collection",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "collection_id")}
    )
    private Collections collection;
}
