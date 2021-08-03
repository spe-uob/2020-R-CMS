package com.leon.dynamiccolumn.repository;

import com.leon.dynamiccolumn.projectentity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
}
