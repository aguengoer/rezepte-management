package com.technikum.rezeptemanagement.models.request;

public class GruppeCreateRequest {

    private String name;

    public GruppeCreateRequest() {
    }

    public GruppeCreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
