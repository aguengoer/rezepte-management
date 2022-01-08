package com.technikum.rezeptemanagement.Entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "rm_kategorie")
public class KategorieEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, columnDefinition = "CHAR(36)")
    private UUID id;

    @Nationalized
    @Column(name = "name", unique = true)
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "rm_kategorie_user_entity_list",
            joinColumns = @JoinColumn(name = "kategorie_entity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_entity_list_id", referencedColumnName = "id"))
    private List<UserEntity> userEntityList = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }
}
