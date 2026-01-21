package com.app.ecom;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

//    @Autowired
    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(userService.fetchAllUsers(),
                                      HttpStatus.OK);
                          //-------  OR   -----//
//        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        User user = userService.fetchUser(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUsers(@RequestBody User user) {
         userService.addUser(user);
         return ResponseEntity.ok("User added Successfully.");
    }

}
