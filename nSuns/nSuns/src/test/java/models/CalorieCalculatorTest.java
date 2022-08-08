package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalorieCalculatorTest {
    @Test
    void manBasicCalorieCalculate() {
        CalorieCalculator calorieCalculator = new CalorieCalculator();

        calorieCalculator.manBasicCalorie(82, 182, 28);

        assertEquals(66 + 13.8 * 82 + 5 * 182 - 6.8 * 28, calorieCalculator.basicCalorie());
    }

    @Test
    void womanBasicCalorieCalculate() {
        CalorieCalculator calorieCalculator = new CalorieCalculator();

        calorieCalculator.womanBasicCalorie(50, 160, 28);

        assertEquals(655 + 9.6 * 50 + 1.8 * 160 - 4.7 * 28, calorieCalculator.basicCalorie());
    }

}