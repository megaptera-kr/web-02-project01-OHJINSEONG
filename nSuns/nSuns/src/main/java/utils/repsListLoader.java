package utils;

import models.Reps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class repsListLoader {
    public static List<Reps> load() throws FileNotFoundException {
        List<Reps> repss = new ArrayList<>();

        File file = new File("repsLoader.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Reps reps = parseReps(line);

            repss.add(reps);
        }
        return repss;
    }

    private static Reps parseReps(String line) {
        String[] words = line.split(",");

        String type = words[0];
        int set1Rep = Integer.parseInt(words[1]);
        int set2Rep = Integer.parseInt(words[2]);
        int set3Rep = Integer.parseInt(words[3]);
        int set4Rep = Integer.parseInt(words[4]);
        int set5Rep = Integer.parseInt(words[5]);
        int set6Rep = Integer.parseInt(words[6]);
        int set7Rep = Integer.parseInt(words[7]);
        int set8Rep = Integer.parseInt(words[8]);

        Reps reps = new Reps(type,set1Rep,set2Rep,set3Rep,set4Rep,set5Rep,set6Rep,set7Rep,set8Rep);

        return reps;
    }
}
