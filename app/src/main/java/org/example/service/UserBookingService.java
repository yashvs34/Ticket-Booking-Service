package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Ticket;
import org.example.entities.Train;
import org.example.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserBookingService {
    private User user;
    private List<User> userList;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "../database/users.json";
    private static final String TRAINS_PATH = "../database/trains.json";

    private UserBookingService(User user) throws IOException {
        this.user = user;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(User user) {
        Optional<User> foundUser = userList.stream().filter(u ->
                        Objects.equals(u.getUserId(), user.getUserId()) && Objects.equals(u.getPassword(), user.getPassword()))
                .findFirst();
        if (foundUser.isEmpty()) {
            System.out.println("User '" + user.getUserId() + "' not found.");
        }
        return foundUser.isPresent();
    }

    public boolean signupUser(User user) {
        if (userList.contains(user)) {
            System.out.println("User '" + user.getUserId() + "' is already present");
            return false;
        }

        userList.add(user);
        File usersList = new File(USERS_PATH);
        try {
            objectMapper.writeValue(usersList, usersList);
        } catch (IOException exception) {
            return false;
        }
        return true;
    }

    public void fetchBookings() {
        List<String> tickets = user.getBookedTickets();
        System.out.println(tickets);
    }

//    public boolean cancelBooking(String ticketId) {
//
//    }
//
//    public boolean bookTicket(String source, String destination, String trainId) {
//
//    }
}
