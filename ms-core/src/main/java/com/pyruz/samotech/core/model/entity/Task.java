package com.pyruz.samotech.core.model.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@Table(name = "task")
@Where(clause = "is_deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Task extends BaseEntity<Integer> {

    @NotNull
    @Size(max = 500)
    private String title;

    @NotNull
    @Size(max = 50)
    private String taskCode;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", updatable = false, insertable = false)
    private States states;
}
