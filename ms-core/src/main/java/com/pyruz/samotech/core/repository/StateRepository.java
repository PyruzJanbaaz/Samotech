package com.pyruz.samotech.core.repository;

import com.pyruz.samotech.core.model.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<States, Integer> {

}
