package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.example.constants.RepositoryConstants.USERS_PATH;

public class UserRepositoryLayer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static List<User> usersList;
    private static final File users = new File(USERS_PATH);

    static {
        try{
            usersList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            System.out.println("IOException while reading users");
        }
    }

    private static boolean saveUser(User user) throws IOException {
        if (usersList.contains(user)){
            System.out.println("User already present in DB");
            return false;
        }

        usersList.add(user);
        objectMapper.writeValue(users, usersList);
        return true;
    }

    public static boolean saveUserToDB(User user) {
        try {
            return saveUser(user);
        } catch (IOException e) {
            System.out.println("IO Exception while saving user.");
        }
        return false;
    }

    public static List<User> getAllUsersFromDB() {
        return usersList;
    }

    public static Optional<User> getUserFromDB(String userId) {
        Optional<User> user = usersList.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
        if (user.isEmpty()) {
            System.out.println("No user present with this userId");
        }
        return user;
    }
}
