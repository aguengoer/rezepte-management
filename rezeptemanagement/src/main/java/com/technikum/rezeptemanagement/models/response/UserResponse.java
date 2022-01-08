package com.technikum.rezeptemanagement.models.response;

public class UserResponse {

    private final String id;

    private final String name;

    private final String email;

    private final String username;

    public UserResponse(String id, String name, String email, String username) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
