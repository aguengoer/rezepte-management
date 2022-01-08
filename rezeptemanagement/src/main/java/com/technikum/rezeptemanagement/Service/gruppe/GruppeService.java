package com.technikum.rezeptemanagement.Service.gruppe;

import com.technikum.rezeptemanagement.Entity.GruppeEntity;
import com.technikum.rezeptemanagement.Entity.RezeptEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface GruppeService {

    GruppeEntity save(GruppeEntity gruppeEntity);

    List<GruppeEntity> getAll();

    String deleteById(UUID id);

    GruppeEntity findById(UUID id);

    List<RezeptEntity> findAllRezepteById(UUID uuid);

    List<GruppeEntity> getAllByUserName(String username);
}
