package com.technikum.rezeptemanagement.models.request;

import java.util.List;

public class RezepteRequest {

    private String name;

    private String notizen;

    private String description;

    private List<ZutatRequest> zutatRequests;

    private String kategorieId;

    private List<String> gruppenIds;

    public RezepteRequest() {
    }

    public RezepteRequest(String name, String notizen, String description,
                          List<ZutatRequest> zutatRequests,
                          String kategorieId, List<String> gruppenIds) {
        this.name = name;
        this.notizen = notizen;
        this.description = description;
        this.zutatRequests = zutatRequests;
        this.kategorieId = kategorieId;
        this.gruppenIds = gruppenIds;
    }

    public String getName() {
        return name;
    }

    public String getNotizen() {
        return notizen;
    }

    public String getDescription() {
        return description;
    }

    public List<ZutatRequest> getZutatRequests() {
        return zutatRequests;
    }

    public String getKategorieId() {
        return kategorieId;
    }

    public List<String> getGruppenIds() {
        return gruppenIds;
    }
}
