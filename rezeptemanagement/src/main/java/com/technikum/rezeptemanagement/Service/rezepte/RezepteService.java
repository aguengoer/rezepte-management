package com.technikum.rezeptemanagement.Service.rezepte;

import com.technikum.rezeptemanagement.Entity.RezeptEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface RezepteService {

    RezeptEntity save(RezeptEntity rezeptEntity);

    List<RezeptEntity> getAll();

    String deleteById(UUID fromString);

    RezeptEntity findById(UUID uuid);

    List<RezeptEntity> findAllByGruppeId(UUID gruppeId);
}
