package models;

public class LifeStyle {
    private String style;
    private double activityIntensity;


    public LifeStyle(String style, double activityIntensity) {
        this.style = style;


        this.activityIntensity = activityIntensity;
    }

    @Override
    public String toString() {
        return style + "    활동 강도: " + activityIntensity;
    }

    public String style(){
        return style;
    }

    public double activityIntensity(){
        return activityIntensity;
    }
}

