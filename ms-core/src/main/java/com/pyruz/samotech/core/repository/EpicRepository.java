package com.pyruz.samotech.core.repository;

import com.pyruz.samotech.core.model.entity.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Integer> {

    List<Epic> findEpicByIdIn(List<Integer> epicIds);

}
