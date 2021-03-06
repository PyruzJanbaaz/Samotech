package com.pyruz.samotech.uaa.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity<T extends Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;
    protected Timestamp createDate = new Timestamp(System.currentTimeMillis());
    protected Timestamp updateDate;
    protected Boolean isActive = false;
    protected Boolean isDeleted = false;

}
