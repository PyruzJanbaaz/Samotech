package com.pyruz.samotech.core.repository;

import com.pyruz.samotech.core.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findProjectByIdIn(List<Integer> projectIds);
    List<Project> findProjectByCollectionId(Integer collectionId);

}
