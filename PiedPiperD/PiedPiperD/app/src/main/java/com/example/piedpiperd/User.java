package com.example.piedpiperd;

public class User
{
    String token;
    String name;

    public User(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getId() {
        return token;
    }

    public void setId(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
