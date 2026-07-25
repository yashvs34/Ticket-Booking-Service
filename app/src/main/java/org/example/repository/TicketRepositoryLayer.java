package org.example.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.constants.RepositoryConstants.TICKETS_PATH;

public class TicketRepositoryLayer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static List<Ticket> ticketsList = new ArrayList<>();
    private static final File tickets = new File(TICKETS_PATH);

    private static boolean saveTicket(Ticket ticket) throws IOException {
        if (ticketsList.contains(ticket)){
            System.out.println("Ticket already present in DB");
            return false;
        }

        ticketsList.add(ticket);
        objectMapper.writeValue(tickets, ticketsList);
        return true;
    }

    public static boolean saveTicketToDB(Ticket ticket) {
        try {
            return saveTicket(ticket);
        } catch (IOException e) {
            System.out.println("IO Exception while saving ticket.");
        }
        return false;
    }

    public static boolean deleteTicket(Ticket ticket) {
        if(!ticketsList.contains(ticket)) {
            System.out.println("No ticket found");
        }

        return ticketsList.remove(ticket);
    }

    public static List<Ticket> getAllTicketsFromDB() {
        return ticketsList;
    }

    public static Optional<Ticket> getTicketFromDB(String ticketId) {
        Optional<Ticket> ticket = ticketsList.stream().filter(u -> u.getTicketId().equals(ticketId)).findFirst();
        if (ticket.isEmpty()) {
            System.out.println("No ticket present with this ticketId");
        }
        return ticket;
    }
}
