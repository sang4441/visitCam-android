package com.example.ezequiel.camera2;

public class Customer {

    private int id;
    private int age;
    private int gender;
    private String url;

    public Customer(int age, int gender, String url) {
//        this.id = id;
        this.age = age;
        this.gender = gender;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
