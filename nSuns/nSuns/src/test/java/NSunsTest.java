import application.NSuns;
import models.Reps;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NSunsTest {
    @Test
    void loadReps() throws FileNotFoundException {
        NSuns nSuns = new NSuns();
        List<Reps> reps = nSuns.loadReps();

        assertNotNull(reps);

        Reps reps1 = reps.get(0);

        assertEquals(reps1, reps.get(0));
    }
}