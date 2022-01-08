package com.technikum.rezeptemanagement.models.request;

import java.util.List;

public class GruppeCreateRequestUpdate {

    private String name;

    private List<String> usernames;

    private List<String> rezeptIds;

    public GruppeCreateRequestUpdate() {
    }

    public GruppeCreateRequestUpdate(String name, List<String> usernames, List<String> rezeptIds) {
        this.name = name;
        this.usernames = usernames;
        this.rezeptIds = rezeptIds;
    }

    public String getName() {
        return name;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public List<String> getRezeptIds() {
        return rezeptIds;
    }
}
