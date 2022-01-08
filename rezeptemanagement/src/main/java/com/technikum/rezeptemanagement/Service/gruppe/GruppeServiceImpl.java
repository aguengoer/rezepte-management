package com.technikum.rezeptemanagement.Service.gruppe;

import com.technikum.rezeptemanagement.Entity.GruppeEntity;
import com.technikum.rezeptemanagement.Entity.RezeptEntity;
import com.technikum.rezeptemanagement.Entity.UserEntity;
import com.technikum.rezeptemanagement.Repository.GruppeRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class GruppeServiceImpl implements GruppeService {

    private final GruppeRepository gruppeRepository;

    public GruppeServiceImpl(GruppeRepository gruppeRepository) {this.gruppeRepository = gruppeRepository;}

    @Override
    public GruppeEntity save(GruppeEntity gruppeEntity) {
        return gruppeRepository.save(gruppeEntity);
    }

    @Override
    public List<GruppeEntity> getAll() {
        return gruppeRepository.findAll();
    }

    @Override
    public String deleteById(UUID id) {
        gruppeRepository.deleteById(id);

        return id.toString();
    }

    @Override
    public GruppeEntity findById(UUID id) {
        Optional<GruppeEntity> byId = gruppeRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }

        throw new IllegalArgumentException(String.format("Gruppe %s is not available", id));
    }

    @Override
    public List<RezeptEntity> findAllRezepteById(UUID uuid) {
        GruppeEntity gruppeEntity = findById(uuid);

        return gruppeEntity.getRezeptEntityList();
    }

    @Override
    public List<GruppeEntity> getAllByUserName(String username) {
        List<GruppeEntity> allGruppen = getAll();
        List<GruppeEntity> gruppeEntitiesToReturn = new ArrayList<>();

        for (GruppeEntity gruppe : allGruppen) {
            List<UserEntity> userEntityList = gruppe.getUserEntityList();
            if (userEntityList.stream().anyMatch(user -> user.getUsername().equals(username))) {
                gruppeEntitiesToReturn.add(gruppe);
            }
        }

        return gruppeEntitiesToReturn;
    }


}
