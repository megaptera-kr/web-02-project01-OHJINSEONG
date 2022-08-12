package utils;

import models.TraningWeight1RM;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainingWeightListLoader {
    public static List<TraningWeight1RM> load() throws FileNotFoundException {
        List<TraningWeight1RM> traningWeight1RMs = new ArrayList<>();

        File file = new File("trainingWeightLoader.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            TraningWeight1RM traningWeight1RM = parsetraningWeight1RM(line);

            traningWeight1RMs.add(traningWeight1RM);
        }

        return traningWeight1RMs;
    }

    private static TraningWeight1RM parsetraningWeight1RM(String line) {
        String[] words = line.split(",");

        double benchPress = Double.parseDouble(words[0]);
        double squt = Double.parseDouble(words[1]);
        double overHeadPress = Double.parseDouble(words[2]);
        double deadLift = Double.parseDouble(words[3]);
        int week = Integer.parseInt(words[4]);

        TraningWeight1RM traningWeight1RM = new TraningWeight1RM(benchPress, squt, overHeadPress, deadLift, week);

        return traningWeight1RM;
    }
}
