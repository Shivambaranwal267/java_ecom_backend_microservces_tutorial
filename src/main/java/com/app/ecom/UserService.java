package com.app.ecom;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
=======
>>>>>>> ebccb94d14e0982c92e9caf5414d341d52fbce17
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
<<<<<<< HEAD
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private List<User> userList = new ArrayList<>();
//    private Long nextId = 1L;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public List<User> fetchAllUsers() {
//        return userList;
        return userRepository.findAll();
    }

    public void addUser(User user) {
//        user.setId(nextId++);

//        userList.add(user);
        userRepository.save(user);
=======
public class UserService {

    private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    public List<User> fetchAllUsers() {
        return userList;
    }

    public void addUser(User user) {
        user.setId(nextId++);
        userList.add(user);
>>>>>>> ebccb94d14e0982c92e9caf5414d341d52fbce17

    }

    public Optional<User> fetchUser(Long id) {
//        for(User user : userList) {
//            if(user.getId().equals(id)){
//                return user;
//            }
//        }
//        return null;
<<<<<<< HEAD
//        return userList.stream().filter(
//                user -> user.getId().equals(id)
//        ).findFirst();
        return userRepository.findById(id);
=======
        return userList.stream().filter(
                user -> user.getId().equals(id)
        ).findFirst();
>>>>>>> ebccb94d14e0982c92e9caf5414d341d52fbce17
    }


    public boolean updateUser(Long id, User updatedUser) {
//        for (User user : userList) {
//            if (user.getId().equals(id)) {
//                user.setFirstName(updatedUser.getFirstName());
//                user.setLastName(updatedUser.getLastName());
//                return Optional.of(user);
//            }
//        }
//        return Optional.empty();

<<<<<<< HEAD
//        return userList.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .map(existingUser -> {
//                    existingUser.setFirstName(updatedUser.getFirstName());
//                    existingUser.setLastName(updatedUser.getLastName());
//                    return true;
//                }).orElse(false);

        return userRepository.findById(id)
                .map(existingUser-> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    userRepository.save(existingUser);
=======
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
>>>>>>> ebccb94d14e0982c92e9caf5414d341d52fbce17
                    return true;
                }).orElse(false);
    }
}
