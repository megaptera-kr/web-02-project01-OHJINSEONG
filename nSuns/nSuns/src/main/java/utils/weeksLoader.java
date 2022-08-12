package utils;

import models.Weeks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class weeksLoader {
    public static Weeks load() throws FileNotFoundException {
        int week = 0;

        File file = new File("weekLoader.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            week = Integer.parseInt(line);
        }

        return new Weeks(week);
    }
}
