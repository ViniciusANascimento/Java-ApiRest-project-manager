package org.api_FullStack.controller;

import org.api_FullStack.model.Project;
import org.api_FullStack.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http:127.0.0.1:5500")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id) {
        var project = projectService.findById(id);
        return ResponseEntity.ok(project);
    }
    //Criado desvio para quando utilizado em localhost

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Project projectToCreate) {
        //Criado no modo TryCatch para que a mensagem de retorno seja reaproveitavel.
        try {
            var projectCreated = projectService.create(projectToCreate);
            return new ResponseEntity<>(projectCreated, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping({"{id}"})
    public ResponseEntity<?> updateProject(@PathVariable("id") Long projectId, @RequestBody Project projectToUpdate) {
        try {
            projectService.update(projectId, projectToUpdate);
            return new ResponseEntity<>(projectToUpdate, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Project project) {
        try {
            projectService.delete(id, project);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}