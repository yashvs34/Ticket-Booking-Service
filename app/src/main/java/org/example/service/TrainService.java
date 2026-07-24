package org.example.service;

import org.example.entities.Train;
import org.example.repository.TrainRepositoryLayer;

import java.util.List;
import java.util.Optional;

public class TrainService {
    public List<Train> getAllTrains() {
        return TrainRepositoryLayer.getAllTrainsFromDB();
    }

    public void getTrainInfo(String trainId) {
        Optional<Train> train = TrainRepositoryLayer.getTrainFromDB(trainId);
        if (train.isEmpty()) {
            System.out.println("No train present with this trainId");
            return;
        }
        System.out.println(train.get());
    }

    public void getSeatsInfo(String trainId) {
        Optional<Train> train = TrainRepositoryLayer.getTrainFromDB(trainId);
        if (train.isEmpty()) {
            System.out.println("No train found with given ID");
            return;
        }
        System.out.println(train.get().getSeats());
    }
}
