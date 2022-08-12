package models;

public class User {
    private String gender;
    private int age;
    private int height;
    private int weight;
    private String name;
    private String findGender;

    public User(String gender, int age, int height, int weight, String name) {
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.name = name;
    }

    public void updateGender(String gender) {
        findGender = gender;
    }

    public String userGender() {
        return findGender;
    }

    public String toString() {
        return gender + "," + age + "," + height + "," + weight + "," + name;
    }

    public int weight() {
        return weight;
    }

    public int height() {
        return height;
    }

    public int age() {
        return age;
    }

    public String gender() {
        return gender;
    }

    public String name() {
        return name;
    }
}
