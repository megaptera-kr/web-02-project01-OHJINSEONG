package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TraningWeight1RMTest {
    @Test
    void creation(){
        TraningWeight1RM traningWeight1RM = new TraningWeight1RM(100,200,60,200,0);
    }

    @Test
    void benchPress() {
        TraningWeight1RM traningWeight1RM = new TraningWeight1RM(100, 200, 60, 200,0);

        assertEquals(100,traningWeight1RM.benchPress());
    }
}