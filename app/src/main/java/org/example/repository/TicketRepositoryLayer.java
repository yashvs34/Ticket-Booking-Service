package org.example.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Ticket;

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
}
