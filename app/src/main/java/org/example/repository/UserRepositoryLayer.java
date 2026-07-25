package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.example.constants.RepositoryConstants.USERS_PATH;

public class UserRepositoryLayer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static List<User> usersList = new ArrayList<>();
    private static final File users = new File(USERS_PATH);

    static {
        try{
            usersList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            System.out.println("IOException while reading users: " + e);
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
        return usersList.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
    }

    public static boolean updateUser(User user) {
        Optional<User> user1 = usersList
                .stream()
                .filter(u -> !Objects.equals(u.getUserId(), user.getUserId())).findFirst();
        if (user1.isEmpty()) {
            System.out.println("No user found with given ID");
            return false;
        }
        usersList.remove(user1.get());
        usersList.add(user);
        try {
            objectMapper.writeValue(users, usersList);
            return true;
        } catch (IOException e) {
            System.out.println("IOException while writing users");
        }
        return false;
    }
}
