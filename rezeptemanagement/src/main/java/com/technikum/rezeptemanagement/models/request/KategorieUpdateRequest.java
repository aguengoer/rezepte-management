package com.technikum.rezeptemanagement.models.request;

import java.util.List;

public class KategorieUpdateRequest {

    private String name;

    private List<String> usernames;

    public KategorieUpdateRequest() {
    }

    public KategorieUpdateRequest(String name, List<String> usernames) {
        this.name = name;
        this.usernames = usernames;
    }

    public String getName() {
        return name;
    }

    public List<String> getUsernames() {
        return usernames;
    }
}
