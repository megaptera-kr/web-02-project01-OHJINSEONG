package utils;

import models.TraningWeight1RM;
import models.User;

import java.io.FileWriter;
import java.io.IOException;

public class SaveTrainingWeight {
    public SaveTrainingWeight(TraningWeight1RM traningWeight1RM) throws IOException {
        FileWriter fileWriter = new FileWriter("trainingWeightLoader.csv");

        fileWriter.write(traningWeight1RM.toString() + "\n");

        fileWriter.close();
    }
}
