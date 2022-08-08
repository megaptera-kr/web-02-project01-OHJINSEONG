package models;

public class User {

    private String gender;
    private int age;
    private int height;
    private int weight;

    public User(String gender, int age, int height, int weight) {
        this.gender = gender;

        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public void updateManGender() {
        gender = "남성";
    }

    public void updateWomanGender() {
        gender = "여성";
    }

    public String userGender(){
        return gender;
    }

    public String toString() {
        return gender + "," + age + "," + height + "," + weight;
    }
}
