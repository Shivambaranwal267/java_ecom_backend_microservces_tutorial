package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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

    }

    public Optional<User> fetchUser(Long id) {
//        for(User user : userList) {
//            if(user.getId().equals(id)){
//                return user;
//            }
//        }
//        return null;
//        return userList.stream().filter(
//                user -> user.getId().equals(id)
//        ).findFirst();
        return userRepository.findById(id);
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
                    return true;
                }).orElse(false);
    }
}
