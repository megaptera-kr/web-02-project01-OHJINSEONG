package utils;

import models.TraningWeight1RM;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveTrainingWeightList {
    public SaveTrainingWeightList(List<TraningWeight1RM> traningWeight1RMs) throws IOException {
        FileWriter fileWriter = new FileWriter("trainingWeightLoader.csv");

        for(TraningWeight1RM traningWeight1RM : traningWeight1RMs) {
            fileWriter.write(traningWeight1RM.toString() + "\n");
        }
        fileWriter.close();
    }
}
