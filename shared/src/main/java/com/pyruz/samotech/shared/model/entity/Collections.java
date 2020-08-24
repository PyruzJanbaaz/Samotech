package com.pyruz.samotech.shared.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

}
