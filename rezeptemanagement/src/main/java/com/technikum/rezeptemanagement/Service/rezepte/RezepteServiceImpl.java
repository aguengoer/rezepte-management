package com.technikum.rezeptemanagement.Service.rezepte;

import com.technikum.rezeptemanagement.Entity.GruppeEntity;
import com.technikum.rezeptemanagement.Entity.RezeptEntity;
import com.technikum.rezeptemanagement.Repository.RezeptRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RezepteServiceImpl implements RezepteService {

    private final RezeptRepository rezeptRepository;

    public RezepteServiceImpl(RezeptRepository rezeptRepository) {this.rezeptRepository = rezeptRepository;}

    @Override
    public RezeptEntity save(RezeptEntity rezeptEntity) {
        return rezeptRepository.save(rezeptEntity);
    }

    @Override
    public List<RezeptEntity> getAll() {
        return rezeptRepository.findAll();
    }

    @Override
    public String deleteById(UUID id) {
        rezeptRepository.deleteById(id);

        return id.toString();
    }

    @Override
    public RezeptEntity findById(UUID id) {
        Optional<RezeptEntity> byId = rezeptRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }

        throw new IllegalArgumentException(String.format("Kategorie %s is not available", id));
    }

    @Override
    public List<RezeptEntity> findAllByGruppeId(UUID gruppeId) {
        List<RezeptEntity> allRezepte = rezeptRepository.findAll();
        List<RezeptEntity> newAddList = new ArrayList<>();
        for (RezeptEntity rezeptEntity : allRezepte) {
            List<GruppeEntity> gruppeEntityList = rezeptEntity.getGruppeEntityList();
            if (gruppeEntityList.stream().anyMatch(gruppe -> gruppe.getId().equals(gruppeId))) {
                newAddList.add(rezeptEntity);
            }
        }
        return newAddList;
    }
}
