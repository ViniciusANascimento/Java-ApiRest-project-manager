package org.api_FullStack.service;

import org.api_FullStack.model.Project;

import java.util.List;

public interface ProjectService {
    Project findById(Long id);
    List<Project> findByUserId(Long idUser);
    Project create(Project projectCreate);
    Project update(Project projectUpdate);
    void delete(Long id, Project projectDelete);
}
