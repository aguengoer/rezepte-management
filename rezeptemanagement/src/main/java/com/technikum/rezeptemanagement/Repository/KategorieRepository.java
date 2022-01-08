package com.technikum.rezeptemanagement.Repository;

import com.technikum.rezeptemanagement.Entity.KategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface KategorieRepository extends JpaRepository<KategorieEntity, UUID> {

    @Query("SELECT kategorie from KategorieEntity kategorie where lower(kategorie.name) = lower(:name)")
    Optional<KategorieEntity> findByName(@Param("name") String name);
}
