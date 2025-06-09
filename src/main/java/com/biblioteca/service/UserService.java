package com.biblioteca.service;

import com.biblioteca.model.User;
import com.biblioteca.repository.UserRepository;

import java.util.List;

public class UserService {
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User login(String username, String password) {
    for (User user : userRepository.findAll()) {
      if (user.getUsername().equals(username) && user.checkPassword(password)) {
        return user;
      }
    }
    return null; // Login fallito
  }

  public void registerUser(User user) {
    userRepository.save(user);
    System.out.println("Utente registrato: " + user.getName());
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
