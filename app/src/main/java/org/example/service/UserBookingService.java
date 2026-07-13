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
        List<Ticket> tickets = user.getBookedTickets();
        System.out.println(tickets);
    }

    public boolean cancelBooking(String ticketId) {
        List<Ticket> tickets = user.getBookedTickets();
        List<Ticket> updatedTickets = tickets.stream().filter(ticket -> !Objects.equals(ticket.getTicketId(), ticketId)).toList();
        user.setBookedTickets(updatedTickets);
        List<User> updatedUserList = userList.stream().filter(user1 -> user1.getUserId().equals(user.getUserId())).toList();
        updatedUserList.add(user);
        File usersList = new File(USERS_PATH);
        try {
            objectMapper.writeValue(usersList, updatedUserList);
        } catch (IOException e) {
            return false;
        }

        Optional<Ticket> t = tickets.stream().filter(ticket -> Objects.equals(ticket.getTicketId(), ticketId)).findFirst();
        if (t.isEmpty()) {
            return false;
        }
        Train train = t.get().getTrain();
        List<List<Boolean>> seats = train.getSeats();

        for (List<Boolean> seat : seats) {
            for (int j = 0; j < seat.size(); j++) {
                if (seat.get(j)) {
                    seat.set(j, false);
                    return true;
                }
            }
        }
        train.setSeats(seats);
        File trainList = new File(TRAINS_PATH);
        objectMapper.writeValue(trainList, );

        return false;
    }

    public boolean bookTicket(String source, String destination, String trainId) {

    }
}
