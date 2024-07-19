package org.api_FullStack.controller;


import org.api_FullStack.model.acess.User;
import org.api_FullStack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http:127.0.0.1:5500")
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @CrossOrigin(origins = "http:127.0.0.1:5500")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody User userToCreate){
       try {
           var userCreated = userService.create(userToCreate);
           return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
       }catch(NoSuchElementException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}

