package utils;

import models.TraningWeight1RM;
import models.Weeks;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveWeeks {
    public SaveWeeks(Weeks weeks) throws IOException {
        FileWriter fileWriter = new FileWriter("weekLoader.csv");

            fileWriter.write(weeks.toString() + "\n");

        fileWriter.close();
    }
}
