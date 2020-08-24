package com.pyruz.samotech.core.model.entity;

import com.pyruz.samotech.shared.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@Table(name = "states")
@Where(clause = "is_deleted = false")
@AllArgsConstructor
@NoArgsConstructor
public class States extends BaseEntity<Integer> {

    @NotNull
    @Size(max = 50)
    private String title;

    @NotNull
    @Size(max = 50)
    private String stateCode;

    @Size(max = 50)
    private String caption;

}
