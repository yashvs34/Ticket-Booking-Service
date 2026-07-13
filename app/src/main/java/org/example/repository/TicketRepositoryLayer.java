package org.example.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Ticket;
import org.example.entities.Train;
import org.example.entities.User;

import java.util.List;

public class TicketRepositoryLayer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveTicketToDB(Ticket ticket) {

    }

//    public List<User> getAllTicketsFromDB() {
//
//    }
//
//    public User getTicketFromDB(String ticketId) {
//
//    }

    public boolean updateTicket(Ticket ticket) {
        // only boarding station can be changed
        return false;
    }
}
