package org.example.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;

public class TrainRepositoryLayer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveTrainToDB(Train train) {

    }

//    public List<User> getAllTrainsFromDB() {
//
//    }
//
//    public User getTrainFromDB(String trainId) {
//
//    }
//
//    public boolean updateTrain(Train train) {
//
//    }
}
