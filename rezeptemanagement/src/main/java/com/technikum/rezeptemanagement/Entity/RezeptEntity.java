package com.technikum.rezeptemanagement.Entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rm_rezepte")
public class RezeptEntity extends AbstractEntity {

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "rm_rezepte_zutat_entity_list",
            joinColumns = @JoinColumn(name = "rezept_entity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "zutat_entity_list_id", referencedColumnName = "id"))
    private List<ZutatEntity> zutatEntityList;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "rm_rezepte_gruppe_entity_list",
            joinColumns = @JoinColumn(name = "rezept_entity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "gruppe_entity_list_id", referencedColumnName = "id"))
    private List<GruppeEntity> gruppeEntityList;

    @ManyToOne
    private KategorieEntity kategorieEntity;

    @Nationalized
    @Column(name = "notizen")
    private String notizen;

    @Nationalized
    @Column(name = "description")
    private String description;

    public List<ZutatEntity> getZutatEntityList() {
        return zutatEntityList;
    }

    public void setZutatEntityList(List<ZutatEntity> zutatEntityList) {
        this.zutatEntityList = zutatEntityList;
    }

    public List<GruppeEntity> getGruppeEntityList() {
        return gruppeEntityList;
    }

    public void setGruppeEntityList(List<GruppeEntity> gruppeEntityList) {
        this.gruppeEntityList = gruppeEntityList;
    }

    public KategorieEntity getKategorieEntity() {
        return kategorieEntity;
    }

    public void setKategorieEntity(KategorieEntity kategorieEntity) {
        this.kategorieEntity = kategorieEntity;
    }

    public String getNotizen() {
        return notizen;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
