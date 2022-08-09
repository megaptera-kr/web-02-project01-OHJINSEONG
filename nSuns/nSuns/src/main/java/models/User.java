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

    public void updateGender(String gender) {
        findGender = gender;
    }

    public String userGender(){
        return findGender;
    }

    public String toString() {
        return gender + "," + age + "," + height + "," + weight;
    }

    public int weight(){
        return weight;
    }

    public int height(){
        return height;
    }

    public int age(){
        return age;
    }

    public String gender(){
        return gender;
    }
}
