//package com.app.ecom;
//
//
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserRepository userRepository;
/// /    private List<User> userList = new ArrayList<>();
/// /    private Long nextId = 1L;
//
////    public UserService(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
//
//    public List<User> fetchAllUsers() {
////        return userList;
//        return userRepository.findAll();
//    }
//
//    public void addUser(User user) {
////        user.setId(nextId++);
//
////        userList.add(user);
//        userRepository.save(user);
//
//
//
//
//
//    public Optional<User> fetchUser(Long id) {
////        for(User user : userList) {
////            if(user.getId().equals(id)){
////                return user;
////            }
////        }
////        return null;
//
////        return userList.stream().filter(
////                user -> user.getId().equals(id)
////        ).findFirst();
//        return userRepository.findById(id);
//
////        return userList.stream().filter(
////                user -> user.getId().equals(id)
////        ).findFirst();
//
//    }
//
//
//    public boolean updateUser(Long id, User updatedUser) {
////        for (User user : userList) {
////            if (user.getId().equals(id)) {
////                user.setFirstName(updatedUser.getFirstName());
////                user.setLastName(updatedUser.getLastName());
////                return Optional.of(user);
////            }
////        }
////        return Optional.empty();
//
//
////        return userList.stream()
////                .filter(user -> user.getId().equals(id))
////                .findFirst()
////                .map(existingUser -> {
////                    existingUser.setFirstName(updatedUser.getFirstName());
////                    existingUser.setLastName(updatedUser.getLastName());
////                    return true;
////                }).orElse(false);
//
//        return userRepository.findById(id)
//                .map(existingUser-> {
//                    existingUser.setFirstName(updatedUser.getFirstName());
//                    existingUser.setLastName(updatedUser.getLastName());
//                    userRepository.save(existingUser);
//
//
//                }
//}

package com.app.ecom.service;

import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.Address;
import com.app.ecom.repository.UserRepository;
import com.app.ecom.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // GET ALL USERS
    public List<UserResponse> fetchAllUsers() {
//        List<User> userList = userRepository.findAll();
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    // ADD USER
    public void addUser(UserRequest userRequest) {
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }


    // GET USER BY ID
    public Optional<UserResponse> fetchUser(Long id) {
        return userRepository.findById(id).map(this::mapToUserResponse);
    }

    // UPDATE USER
    public boolean updateUser(Long id, UserRequest updatedUserRequest) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    updateUserFromRequest(existingUser, updatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                })
                .orElse(false);
    }

    // DELETE USER
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setCity(userRequest.getAddress().getCity());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        }
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }


        return response;
    }
}

