package utils;

import models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadUserBody {
    public static User Loader() throws FileNotFoundException {
        String gender = "";
        int age = 0;
        int height = 0;
        int weight = 0;
        String name = "";

        File file = new File("userBodyLoader.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] words = line.split(",");

            gender = words[0];
            age = Integer.parseInt(words[1]);
            height = Integer.parseInt(words[2]);
            weight = Integer.parseInt(words[3]);
            name = words[4];
        }

        return new User(gender, age, height, weight, name);
    }
}
