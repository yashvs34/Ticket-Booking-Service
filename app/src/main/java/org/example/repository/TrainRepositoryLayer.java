package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.example.constants.RepositoryConstants.TRAINS_PATH;

public class TrainRepositoryLayer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static List<Train> trainsList;
    private static final File trains = new File(TRAINS_PATH);

    static {
        try {
            trainsList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
        } catch (IOException e) {
            System.out.println("IOException while reading trains");
        }
    }

    private static boolean saveTrain(Train train) throws IOException {
        if (trainsList.contains(train)){
            System.out.println("Train already present in DB");
            return false;
        }

        trainsList.add(train);
        objectMapper.writeValue(trains, trainsList);
        return true;
    }

    public static boolean saveTrainToDB(Train train) {
        try {
            return saveTrain(train);
        } catch (IOException e) {
            System.out.println("IO Exception while saving train.");
        }
        return false;
    }

    public static List<Train> getAllTrainsFromDB() {
        return trainsList;
    }

    public static Optional<Train> getTrainFromDB(String trainId) {
        Optional<Train> train = trainsList.stream().filter(u -> u.getTrainId().equals(trainId)).findFirst();
        if (train.isEmpty()) {
            System.out.println("No train present with this trainId");
        }
        return train;
    }
}
