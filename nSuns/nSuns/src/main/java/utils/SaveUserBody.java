package utils;

import models.User;

import java.io.FileWriter;
import java.io.IOException;

public class SaveUserBody {
    public SaveUserBody(User user) throws IOException {
        FileWriter fileWriter = new FileWriter("userBodyLoader.csv");

        fileWriter.write(user.toString() + "\n");

        fileWriter.close();
    }
}
