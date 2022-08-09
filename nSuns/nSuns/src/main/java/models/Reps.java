package models;

public class Reps {


    private String type;
    private int set1Rep;
    private int set2Rep;
    private int set3Rep;
    private int set4Rep;
    private int set5Rep;
    private int set6Rep;
    private int set7Rep;
    private int set8Rep;

    public Reps(String type, int set1Rep, int set2Rep, int set3Rep, int set4Rep, int set5Rep, int set6Rep, int set7Rep, int set8Rep) {
        this.type = type;
        this.set1Rep = set1Rep;
        this.set2Rep = set2Rep;
        this.set3Rep = set3Rep;
        this.set4Rep = set4Rep;
        this.set5Rep = set5Rep;
        this.set6Rep = set6Rep;
        this.set7Rep = set7Rep;
        this.set8Rep = set8Rep;
    }

    public String toString() {
        return type + "," + set1Rep + "," + set2Rep + "," + set3Rep + "," + set4Rep + "," +
                set5Rep + "," + set6Rep + "," + set7Rep + "," + set8Rep;
    }

    public int set3Rep() {
        return set3Rep;
    }

    public double overReps() {
        if (set3Rep >= 11) {
            return 10;
        }
        if (set3Rep >= 8 && set3Rep < 11) {
            return 7.5;
        }
        if (set3Rep >= 5 && set3Rep < 8) {
            return 5;
        }
        if (set3Rep >= 2 && set3Rep < 5) {
            return 2.5;
        }
        return 0;
    }
}
