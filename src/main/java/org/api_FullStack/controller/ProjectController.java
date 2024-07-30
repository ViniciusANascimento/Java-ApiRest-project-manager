package org.api_FullStack.controller;

import org.api_FullStack.model.Project;
import org.api_FullStack.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity<?> findbyUserId(@PathVariable long id) {
        try {
            List<Project> projectsFounded = projectService.findByUserId(id);
            return new ResponseEntity<>(projectsFounded, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
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

    @CrossOrigin
    @PutMapping()
    public ResponseEntity<?> updateProject(@RequestBody Project projectToUpdate) {
        try {
            projectService.update(projectToUpdate);
            return new ResponseEntity<>(projectToUpdate, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
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