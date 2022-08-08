package models;

public class CalorieCalculator {

    private double basicCalorie;
    private double activityCalorie;

    public double basicCalorie() {
        return basicCalorie;
    }

    public void manBasicCalorie(int weight, int height, int age) {
        basicCalorie = (66 + 13.8 * weight + 5 * height - 6.8 * age);
    }

    public void womanBasicCalorie(int weight, int height, int age) {
        basicCalorie = (655 + 9.6 * weight + 1.8 * height - 4.7 * age);
    }

    public void activityCalorieCalculate(double activityIntensity) {
        activityCalorie = basicCalorie * activityIntensity + 500;
    }

    public double activityCalorie() {
        return activityCalorie;
    }
}
