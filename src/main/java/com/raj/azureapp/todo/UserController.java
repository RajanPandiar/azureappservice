package com.raj.azureapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController
{

    @Autowired
    UserRepository userRepository;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try{
            User newUser = new User(user.getId(),user.getFirstName(),user.getLastName(), user.getAddress());
            userRepository.save(newUser);
            return new ResponseEntity<>(newUser,HttpStatus.CREATED);
        }catch (Exception e) {
            throw  new RuntimeException();
        }
    }



    @GetMapping({"/"})
    public ResponseEntity<List<User>> getAllUsers() {
        try{
            List<User> usersList = new ArrayList<User>();
            userRepository.findAll().forEach(usersList::add);
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
