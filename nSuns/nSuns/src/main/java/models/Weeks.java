package models;

public class Weeks {
    private int benchPressIndex = 0;
    private int squtIndex = 0;
    private int overHeadPressIndex = 0;
    private int deadLiftIndex = 0;
    private int week;
    private int startFrameIndex = 0;


    public Weeks(int week) {

        this.week = week;
    }

    public int index(String workOut) {
        return switch (workOut) {
            case "벤치프레스" -> benchPressIndex;
            case "스쿼트" -> squtIndex;
            case "오버헤드프레스" -> overHeadPressIndex;
            case "데드리프트" -> deadLiftIndex;
            default -> 0;
        };
    }

    public void increaseIndex(String workOut) {
        switch (workOut) {
            case "벤치프레스" -> benchPressIndex = 1;
            case "스쿼트" -> squtIndex = 1;
            case "오버헤드프레스" -> overHeadPressIndex = 1;
            case "데드리프트" -> deadLiftIndex = 1;
        }
    }

    public void increaseWeek() {
        week += 1;
    }

    public int week() {
        return week;
    }

    public boolean increase() {
        return benchPressIndex + squtIndex + overHeadPressIndex + deadLiftIndex == 4;
    }

    public void initIndex() {
        benchPressIndex = 0;
        squtIndex = 0;
        overHeadPressIndex = 0;
        deadLiftIndex = 0;
    }

    public int startFrameIndex() {
        return startFrameIndex;
    }

    public void increaseStartFrameIndex() {
        startFrameIndex += 1;
    }

    public void initStartFrameIndex() {
        if (benchPressIndex == 0 && squtIndex == 0 && overHeadPressIndex == 0 && deadLiftIndex == 0) {
            startFrameIndex = 0;
        }
    }
}
