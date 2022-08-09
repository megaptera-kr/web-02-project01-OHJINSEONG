package models;

public class TraingWeightCalculator {
    public double calculate(double weight) {
        double roundWeight = Math.round(weight / 10) * 10;

        if (weight < roundWeight && roundWeight <= (weight + 5)) {
            if ((roundWeight - 1.25) <= weight) {
                return roundWeight;
            }
            if ((roundWeight - 3.75) <= weight && weight < (roundWeight - 1.25)) {
                return roundWeight - 2.5;
            }
            if ((roundWeight - 5) <= weight && weight < (roundWeight - 3.75)) {
                return roundWeight - 5;
            }
        }
        if (weight >= roundWeight && roundWeight > weight - 5) {
            if (weight < (roundWeight + 1.25)) {
                return roundWeight;
            }
            if ((roundWeight + 1.25) <= weight && weight < (roundWeight + 3.75)) {
                return roundWeight + 2.5;
            }
            if ((roundWeight + 3.75) <= weight && weight < (roundWeight + 5)) {
                return roundWeight + 5;
            }
        }
        return 0;
    }
}
