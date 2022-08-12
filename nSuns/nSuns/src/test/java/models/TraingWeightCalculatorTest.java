package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TraingWeightCalculatorTest {
    @Test
    void weightCalcul(){
        TraingWeightCalculator traingWeightCalculator = new TraingWeightCalculator();

        assertEquals(60,traingWeightCalculator.calculate(59));
        assertEquals(52.5,traingWeightCalculator.calculate(53));
        assertEquals(50,traingWeightCalculator.calculate(51));
        assertEquals(70,traingWeightCalculator.calculate(71));
        assertEquals(72.5,traingWeightCalculator.calculate(73));
        assertEquals(45,traingWeightCalculator.calculate(45));
        assertEquals(40,traingWeightCalculator.calculate(40));
        assertEquals(50,traingWeightCalculator.calculate(50));
    }
}
