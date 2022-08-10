package utils;

import models.Reps;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveRepsList {
    public void SaveReps(List<Reps> repss) throws IOException {
        FileWriter fileWriter = new FileWriter("repsLoader.csv");

        for (Reps reps : repss) {
            fileWriter.write(reps.toString() + "\n");
        }

        fileWriter.close();
    }
}
