package com.pyruz.samotech.core.model.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@Table(name = "epic")
@Where(clause = "is_deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Epic extends BaseEntity<Integer> {

    @NotNull
    @Size(max = 500)
    private String title;

    @NotNull
    @Size(max = 50)
    private String epicCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "epic_project",
            joinColumns = {@JoinColumn(name = "epic_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private Project project;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", updatable = false, insertable = false)
    private States states;


}
