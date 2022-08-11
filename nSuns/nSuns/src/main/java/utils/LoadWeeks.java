package utils;

import models.User;
import models.Weeks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadWeeks {
    public static Weeks Loader() throws FileNotFoundException {
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
