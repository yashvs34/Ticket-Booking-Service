package org.example.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNumber;
    private List<List<Boolean>> seats;
    private Map<String, Date> stationTimes;
    private List<String> stations;

    public Train(String trainId, String trainNumber, List<List<Boolean>> seats, Map<String, Date> stationTimes, List<String> stations) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }

    public String getTrainId() {return trainId;}
    public String getTrainNumber() {return trainNumber;}
    public List<List<Boolean>> getSeats() {return seats;}
    public Map<String, Date> getStationTimes() {return stationTimes;}
    public List<String> getStations() {return stations;}

    public void setTrainId(String trainId) {this.trainId = trainId;}
    public void setTrainNumber(String trainNumber) {this.trainNumber = trainNumber;}
    public void setSeats(List<List<Boolean>> seats) {this.seats = seats;}
    public void setStationTimes(Map<String, Date> stationTimes) {this.stationTimes = stationTimes;}
    public void setStations(List<String> stations) {this.stations = stations;}
}
