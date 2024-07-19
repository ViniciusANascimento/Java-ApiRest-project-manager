package org.api_FullStack.controller;

import org.api_FullStack.model.Project;
import org.api_FullStack.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

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
    public ResponseEntity<?> save(@RequestBody Project projectToCreate){
        //Criado no modo TryCatch para que a mensagem de retorno seja reaproveitavel.
        try {
            var projectCreated = projectService.create(projectToCreate);
            return new ResponseEntity<>(projectCreated, HttpStatus.CREATED);
        } catch (NoSuchElementException e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
