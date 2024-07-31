package org.api_FullStack.controller;


import org.api_FullStack.model.User;
import org.api_FullStack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        try {
            var userFound = userService.findById(id);
            return new ResponseEntity<>(userFound, HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody User userToCreate){
       try {
           var userCreated = userService.create(userToCreate);
           return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
       }catch(NoSuchElementException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       } catch (IllegalArgumentException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
       } catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}

