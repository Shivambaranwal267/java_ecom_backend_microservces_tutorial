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
//@RequestMapping("/api/users")
public class UserController {

    //    @Autowired
    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/api/users")
//    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(userService.fetchAllUsers(),
                HttpStatus.OK);
        //-------  OR   -----//
//        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

//        User user = userService.fetchUser(id);
//        if(user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);

        return userService.fetchUser(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUsers(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added Successfully.");
    }

//    @PutMapping("/api/users/{id}")
//    public ResponseEntity<User> updateUserById(
//            @PathVariable Long id,
//            @RequestBody User updatedUser) {
//
//        return userService.updateUser(id, updatedUser)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }


    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Long id, @RequestBody User updatedUser) {

        boolean updated = userService.updateUser(id, updatedUser);
        if (updated)
            return ResponseEntity.ok("User updated successfully.");
        return ResponseEntity.notFound().build();

    }

}
