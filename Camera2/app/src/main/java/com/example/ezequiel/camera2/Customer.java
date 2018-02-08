package com.example.ezequiel.camera2;

import java.util.Date;
import java.util.List;

public class Customer {

    private int id;
    private int age;
    private int gender;


    private String url;
    private String dateEntered;
    private List<String> images;

    public Customer(int id, int age, int gender, List<String> urls, String date) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.images = urls;
        this.dateEntered = date;

    }

    public Customer(int age, int gender, String url) {
        this.id = id;
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

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
