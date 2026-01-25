package com.app.ecom.controller;


import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    //    @Autowired
    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping
//    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return new ResponseEntity<>(userService.fetchAllUsers(),
                HttpStatus.OK);
        //-------  OR   -----//
//        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

//        User user = userService.fetchUser(id);
//        if(user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);

        return userService.fetchUser(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUsers(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
//        return ResponseEntity.ok("User added Successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body("User added Successfully");
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


    @PutMapping("{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Long id, @RequestBody UserRequest updatedUserRequest) {

        boolean updated = userService.updateUser(id, updatedUserRequest);
        if (updated)
            return ResponseEntity.ok("User updated successfully.");
        return ResponseEntity.notFound().build();

    }

}
