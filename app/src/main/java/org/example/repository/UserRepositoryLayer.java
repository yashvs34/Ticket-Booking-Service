package org.example.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.User;

import java.util.List;

public class UserRepositoryLayer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveUserToDB(User user) {

    }

    public List<User> getAllUsersFromDB() {

    }

    public User getUserFromDB(String userId) {

    }

    public boolean updateUser(User user) {

    }
}
