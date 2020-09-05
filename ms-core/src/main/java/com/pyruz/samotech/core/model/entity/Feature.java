package com.pyruz.samotech.core.model.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "feature")
@Where(clause = "is_deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Feature extends BaseEntity<Integer> {

    @NotNull
    @Size(max = 500)
    private String title;

    @NotNull
    @Size(max = 50)
    private String featureCode;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "feature_task",
            joinColumns = {@JoinColumn(name = "feature_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")}
    )
    private List<Task> tasks;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", updatable = false, insertable = false)
    private States states;
}
