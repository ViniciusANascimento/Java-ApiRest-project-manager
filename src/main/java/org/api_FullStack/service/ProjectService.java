package org.api_FullStack.service;

import org.api_FullStack.model.Project;

public interface ProjectService {
    Project findById(Long id);
    Project create(Project projectCreate);
    Project update(Long id, Project projectUpdate);
    void delete(Long id, Project projectDelete);
}
