package com.pyruz.samotech.core.repository;

import com.pyruz.samotech.core.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findTaskByIdIn(List<Integer> taskIds);

}
