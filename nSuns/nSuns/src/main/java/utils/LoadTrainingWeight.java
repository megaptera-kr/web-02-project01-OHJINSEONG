package utils;

import models.TraningWeight1RM;
import models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadTrainingWeight {
    public static TraningWeight1RM Loader() throws FileNotFoundException {
        double benchPress = 0;
        double squt = 0;
        double overHeadPress = 0;
        double deadLift = 0;

        File file = new File("trainingWeightLoader.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] words = line.split(",");

            benchPress = Double.parseDouble(words[0]);
            squt = Double.parseDouble(words[1]);
            overHeadPress = Double.parseDouble(words[2]);
            deadLift = Double.parseDouble(words[3]);

        }

        return new TraningWeight1RM(benchPress, squt, overHeadPress, deadLift);
    }
}
