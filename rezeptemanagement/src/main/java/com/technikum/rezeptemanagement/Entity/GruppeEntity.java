package com.technikum.rezeptemanagement.Entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rm_gruppe")
public class GruppeEntity extends AbstractEntity {

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "rm_gruppe_user_entity_list",
            joinColumns = @JoinColumn(name = "gruppe_entity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_entity_list_id", referencedColumnName = "id"))
    private List<UserEntity> userEntityList;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "rm_rezepte_gruppe_entity_list",
            joinColumns = @JoinColumn(name = "gruppe_entity_list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rezept_entity_id", referencedColumnName = "id"))
    private List<RezeptEntity> rezeptEntityList;

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    public List<RezeptEntity> getRezeptEntityList() {
        return rezeptEntityList;
    }

    public void setRezeptEntityList(List<RezeptEntity> rezeptEntityList) {
        this.rezeptEntityList = rezeptEntityList;
    }
}
