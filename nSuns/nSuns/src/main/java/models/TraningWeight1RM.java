package models;

public class TraningWeight1RM {
    private double benchPress1RM;
    private double squt1RM;
    private double overHeadPress1rm;
    private double deadLift1rm;

    public TraningWeight1RM(double benchPress1RM, double squt1RM,
                            double overHeadPress1rm, double deadLift1rm) {
        this.benchPress1RM = benchPress1RM;
        this.squt1RM = squt1RM;
        this.overHeadPress1rm = overHeadPress1rm;
        this.deadLift1rm = deadLift1rm;
    }

    public double benchPress(){
        return benchPress1RM;
    }

    public double squt(){
        return squt1RM;
    }

    public double overHeadPress(){
        return overHeadPress1rm;
    }

    public double deadLift(){
        return deadLift1rm;
    }
}
