package com.technikum.rezeptemanagement.models.response;

import java.util.List;

public class GruppeResponse {

    private final String id;

    private final String name;

    private final List<UserResponse> userResponses;

    public GruppeResponse(String id, String name, List<UserResponse> userResponses) {
        this.id = id;
        this.name = name;
        this.userResponses = userResponses;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<UserResponse> getUserResponses() {
        return userResponses;
    }
}
