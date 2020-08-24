package com.pyruz.samotech.core.repository;

import com.pyruz.samotech.core.model.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {

    List<Feature> findFeatureByIdIn(List<Integer> featureIds);

}
