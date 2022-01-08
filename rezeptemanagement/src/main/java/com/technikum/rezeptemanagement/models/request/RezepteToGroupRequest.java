package com.technikum.rezeptemanagement.models.request;

public class RezepteToGroupRequest {

    private String gruppeId;

    public RezepteToGroupRequest() {
    }

    public RezepteToGroupRequest(String gruppeId) {
        this.gruppeId = gruppeId;
    }

    public String getGruppeId() {
        return gruppeId;
    }
}
