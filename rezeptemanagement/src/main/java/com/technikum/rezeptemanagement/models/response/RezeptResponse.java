package com.technikum.rezeptemanagement.models.response;

import java.util.List;

public class RezeptResponse {

    private String id;

    private String name;

    private String notizen;

    private String description;

    private KategorieResponse kategorieEntity;

    private List<ZutatResponse> zutatEntityList;

    private List<GruppeResponse> gruppeResponseList;

    public RezeptResponse(String id, String name, String notizen, String description,
                          KategorieResponse kategorieEntity, List<ZutatResponse> zutatEntityList,
                          List<GruppeResponse> gruppeResponseList) {
        this.id = id;
        this.name = name;
        this.notizen = notizen;
        this.description = description;
        this.kategorieEntity = kategorieEntity;
        this.zutatEntityList = zutatEntityList;
        this.gruppeResponseList = gruppeResponseList;
    }

    public String getId() {
        return id;
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

    public KategorieResponse getKategorieEntity() {
        return kategorieEntity;
    }

    public List<ZutatResponse> getZutatEntityList() {
        return zutatEntityList;
    }

    public List<GruppeResponse> getGruppeResponseList() {
        return gruppeResponseList;
    }
}
