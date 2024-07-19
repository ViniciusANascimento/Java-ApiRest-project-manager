package org.api_FullStack.controller;

import org.api_FullStack.model.Project;
import org.api_FullStack.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @CrossOrigin(origins = "http:127.0.0.1:5500")
    @GetMapping("{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id){
        var project = projectService.findById(id);
        return ResponseEntity.ok(project);
    }
    //Criado desvio para quando utilizado em localhost
    @CrossOrigin(origins = "http:127.0.0.1:5500")
    @PostMapping()
    public ResponseEntity<Project> save(@RequestBody Project projectToCreate){
        var projectCreated = projectService.create(projectToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(projectCreated);
    }
}
