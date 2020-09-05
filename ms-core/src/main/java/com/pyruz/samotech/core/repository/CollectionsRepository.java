package com.pyruz.samotech.core.repository;

import com.pyruz.samotech.core.model.entity.Collections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CollectionsRepository extends JpaRepository<Collections, Integer> {
    List<Collections> findCollectionByIdIn(List<Integer> collectionIds);

}
