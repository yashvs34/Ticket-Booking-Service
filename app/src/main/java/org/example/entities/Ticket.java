package org.example.entities;

import java.time.LocalDateTime;
import java.util.Date;

public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private LocalDateTime dateOfTravel;
    private String trainId;

    public Ticket(String ticketId,
                  String userId,
                  String source,
                  String destination,
                  LocalDateTime dateOfTravel,
                  String trainId) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.trainId = trainId;
    }

    public String getTicketId() {return ticketId;}
    public String getUserId() {return userId;}
    public String getSource() {return source;}
    public String getDestination() {return destination;}
    public LocalDateTime getDateOfTravel() {return dateOfTravel;}
    public String getTrainId() {return trainId;}

    public void setTicketId(String ticketId) {this.ticketId = ticketId;}
    public void setUserId(String userId) {this.userId = userId;}
    public void setSource(String source) {this.source = source;}
    public void setDestination(String destination) {this.destination = destination;}
    public void setDateOfTravel(LocalDateTime dateOfTravel) {this.dateOfTravel = dateOfTravel;}
    public void setTrainId(String trainId) {this.trainId = trainId;}
}
