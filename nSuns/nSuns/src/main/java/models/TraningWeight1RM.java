package models;

public class TraningWeight1RM {
    private double benchPress1RM;
    private double squt1RM;
    private double overHeadPress1rm;
    private double deadLift1rm;
    private int week;

    public TraningWeight1RM(double benchPress1RM, double squt1RM,
                            double overHeadPress1rm, double deadLift1rm, int week) {
        this.benchPress1RM = benchPress1RM;
        this.squt1RM = squt1RM;
        this.overHeadPress1rm = overHeadPress1rm;
        this.deadLift1rm = deadLift1rm;
        this.week = week;
    }

    public double benchPress() {
        return benchPress1RM;
    }

    public double squt() {
        return squt1RM;
    }

    public double overHeadPress() {
        return overHeadPress1rm;
    }

    public double deadLift() {
        return deadLift1rm;
    }

    public void increaseWeight(String workOut, double increaseWeight) {
        switch (workOut) {
            case "벤치프레스" -> benchPress1RM += increaseWeight;
            case "스쿼트" -> squt1RM += increaseWeight;
            case "오버헤드프레스" -> overHeadPress1rm += increaseWeight;
            case "데드리프트" -> deadLift1rm += increaseWeight;
        }
    }

    public String toString() {
        return benchPress1RM + "," + squt1RM + "," + overHeadPress1rm + "," + deadLift1rm + "," + week;
    }

    public int week() {
        return week;
    }
}
