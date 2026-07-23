package org.example.service;

import org.example.entities.Ticket;
import org.example.entities.User;
import org.example.repository.TicketRepositoryLayer;
import org.example.repository.TrainRepositoryLayer;
import org.example.repository.UserRepositoryLayer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserBookingService {
    private final User user;

    private UserBookingService(User user) throws IOException {
        this.user = user;
    }

    public Boolean loginUser(User user) {
        Optional<User> foundUser = UserRepositoryLayer.getUserFromDB(user.getUserId());
        if (foundUser.isEmpty()) {
            System.out.println("User '" + user.getUserId() + "' not found.");
        }
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
            TrainRepositoryLayer.clearSeat(trainId);
            return true;
        }
        return false;
    }

    public boolean bookTicket(String source, String destination, String dateOfTravel, String trainId) {
        String ticketId = UUID.randomUUID().toString();
        // create object and fill details
        Ticket newTicket = new Ticket(ticketId, user.getUserId(), source, destination, dateOfTravel, trainId);

        List<String> bookedTickets = user.getBookedTickets();
        bookedTickets.add(ticketId);
        user.setBookedTickets(bookedTickets);
        UserRepositoryLayer.updateUser(user);
        TrainRepositoryLayer.reserveSeat(trainId);
        return true;
    }
}
