package com.example.redis_test;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class UserProfile {
    @JsonSerialize
    private String name;
    @JsonSerialize
    private int age;

    public UserProfile(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
