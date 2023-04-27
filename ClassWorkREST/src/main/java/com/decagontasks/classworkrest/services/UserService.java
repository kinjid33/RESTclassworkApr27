package com.decagontasks.classworkrest.services;

import com.decagontasks.classworkrest.entity.User;
import com.decagontasks.classworkrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User displayUser(Long id){
        Optional<User> tempUser = userRepository.findById(id);
        User user = null;
        if(tempUser.isPresent()){
            user = tempUser.get();
        }
        else {
            throw new RuntimeException("User with id " + id + " does not exist!");
        }
        return user;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user){
        User userInDB = userRepository.findById(id).get();
        userInDB.setUserName(user.getUserName());
        userInDB.setEmail(user.getEmail());
        userInDB.setPassword(user.getPassword());
        userInDB.setId(user.getId());
        return userRepository.save(userInDB);
    }

    public String deleteUser(Long id){
        try {
            userRepository.deleteById(id);
        }
        catch (Exception e) {
            return "User with id: " + id + " not found";
        }
        return "Deleted user with id: " + id + "!";
    }
}
