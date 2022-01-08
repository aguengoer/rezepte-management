package com.technikum.rezeptemanagement.models.response;

public class ZutatResponse {

    private final String id;

    private final String name;

    public ZutatResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
