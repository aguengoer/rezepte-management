package com.technikum.rezeptemanagement.Repository;

import com.technikum.rezeptemanagement.Entity.GruppeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface GruppeRepository extends JpaRepository<GruppeEntity, UUID> {
}
