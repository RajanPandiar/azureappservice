package com.raj.azureapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class UserController
{

    @Autowired
    UserRepository userRepository;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try{
            System.out.println("Hi There");
            User newUser = new User(user.getId(),user.getFirstName(),user.getLastName(), user.getAddress());
            userRepository.save(newUser);
            return new ResponseEntity<>(newUser,HttpStatus.CREATED);
        }catch (Exception e) {
            throw  new RuntimeException();
        }
    }

    @GetMapping({"/api/v1/users"})
    public ResponseEntity<List<User>> getAllUsers() {
        try{
            List<User> usersList = new ArrayList<User>();
            userRepository.findAll().forEach(usersList::add);
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }


    @GetMapping({"/api/v1/users/{id}"})
    public ResponseEntity<User> getUser(@PathVariable String id) {
        try{
            Optional<User> user= userRepository.findById(id);
            return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

        }catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
