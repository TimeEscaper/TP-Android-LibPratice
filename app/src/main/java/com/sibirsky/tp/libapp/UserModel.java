package com.sibirsky.tp.libapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sibirsky on 21.03.17.
 */

public class UserModel {
    private String fullName;
    private Integer age;
    private Double weight;
    private String gender;
    private String married;
    @SerializedName("protected")
    private Integer isProtected;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public Integer getProtected() {
        return isProtected;
    }

    public void setProtected(Integer aProtected) {
        isProtected = aProtected;
    }
}
