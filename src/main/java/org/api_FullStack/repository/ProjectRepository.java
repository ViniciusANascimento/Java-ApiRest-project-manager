package org.api_FullStack.repository;

import org.api_FullStack.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsById(long id);

    @Query("SELECT p FROM tb_project p WHERE p.userID = :userId")
    List<Project> findProjectsByUserId(long userId);
}
