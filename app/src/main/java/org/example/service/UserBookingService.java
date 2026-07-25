package org.example.service;

import org.example.constants.ServiceConstants;
import org.example.entities.Ticket;
import org.example.entities.User;
import org.example.repository.TicketRepositoryLayer;
import org.example.repository.TrainRepositoryLayer;
import org.example.repository.UserRepositoryLayer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserBookingService {
    private User user;

    public Boolean loginUser(String userId, String password) {
        Optional<User> foundUser = UserRepositoryLayer.getUserFromDB(userId);
        if (foundUser.isEmpty()) {
            System.out.println("User '" + userId + "' not found.");
        } else if (!Objects.equals(foundUser.get().getPassword(), password)) {
            System.out.println("Password for the userId " + userId + " is incorrect");
            return false;
        }
        this.user = foundUser.orElse(null);
        return foundUser.isPresent();
    }

    public boolean signupUser(User user) {
        final String userId = user.getUserId();
        if (UserRepositoryLayer.getUserFromDB(userId).isPresent()) {
            System.out.println("User '" + userId + "' is already present");
            return false;
        }

        return UserRepositoryLayer.saveUserToDB(user);
    }

    public void fetchBookings() {
        List<Ticket> tickets = user.getBookedTickets().stream()
                .map(TicketRepositoryLayer::getTicketFromDB)
                .flatMap(Optional::stream)
                .toList();
        System.out.println(tickets);
    }

    public boolean cancelBooking(String ticketId) {
        Optional<Ticket> ticket = TicketRepositoryLayer.getTicketFromDB(ticketId);
        if (ticket.isPresent()) {
            String trainId = ticket.get().getTrainId();
            TicketRepositoryLayer.deleteTicket(ticket.get());
            return TrainRepositoryLayer.clearSeat(trainId);
        }
        return false;
    }

    public boolean bookTicket(String source, String destination, String dateOfTravel, String trainId) {
        String ticketId = UUID.randomUUID().toString();
        LocalDateTime dateTime = LocalDateTime.parse(dateOfTravel, ServiceConstants.FORMATTER);
        Ticket newTicket = new Ticket(ticketId, user.getUserId(), source, destination, dateTime, trainId);

        List<String> bookedTickets = user.getBookedTickets();
        bookedTickets.add(ticketId);
        user.setBookedTickets(bookedTickets);
        UserRepositoryLayer.updateUser(user);
        TrainRepositoryLayer.reserveSeat(trainId);
        return true;
    }
}
