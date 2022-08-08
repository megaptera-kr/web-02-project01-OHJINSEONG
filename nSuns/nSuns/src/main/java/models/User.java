package models;

public class User {
    private String gender;
    private int age;
    private int height;
    private int weight;

    private String findGender;
    public User(String gender, int age, int height, int weight) {
        this.gender = gender;

        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public void updateManGender(String gender) {
        findGender = gender;
    }

    public void updateWomanGender() {
        findGender = "여성";
    }

    public String userGender(){
        return findGender;
    }

    public String toString() {
        return gender + "," + age + "," + height + "," + weight;
    }
}
