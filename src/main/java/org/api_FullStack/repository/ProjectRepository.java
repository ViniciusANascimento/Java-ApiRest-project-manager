package org.api_FullStack.repository;

import org.api_FullStack.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    boolean existsById(long id);
}
