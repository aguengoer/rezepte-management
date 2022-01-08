package com.technikum.rezeptemanagement.Service.kategorie;

import com.technikum.rezeptemanagement.Entity.KategorieEntity;
import com.technikum.rezeptemanagement.Entity.UserEntity;
import com.technikum.rezeptemanagement.Repository.KategorieRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class KategorieServiceImpl implements KategorieService {

    private final String VORSPEISSE = "Vorspeiße";

    private final String HAUPTSPEISSE = "Hauptspeiße";

    private final String NACHSPEISSE = "Nachspeiße";

    private final KategorieRepository kategorieRepository;

    public KategorieServiceImpl(KategorieRepository kategorieRepository) {this.kategorieRepository = kategorieRepository;}

    @Override
    public KategorieEntity save(KategorieEntity kategorieEntity) {
        return kategorieRepository.save(kategorieEntity);
    }

    @Override
    public List<KategorieEntity> getAll() {
        return kategorieRepository.findAll();
    }

    @Override
    public KategorieEntity findById(UUID id) {
        Optional<KategorieEntity> byId = kategorieRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }

        throw new IllegalArgumentException(String.format("Kategorie %s is not available", id));
    }

    @Override
    public String deleteById(UUID id) {
        kategorieRepository.deleteById(id);

        return id.toString();
    }

    @Override
    public void createDefaultKategoriesForUser(UserEntity userEntity) {
        List<KategorieEntity> defaultKategoriesValues = getOrCreateDefaultKategoriesValues();
        for (KategorieEntity katEntity : defaultKategoriesValues) {
            katEntity.getUserEntityList().add(userEntity);
            kategorieRepository.save(katEntity);
        }
    }

    private List<KategorieEntity> getOrCreateDefaultKategoriesValues() {
        return List.of(evaluateSpeiße(VORSPEISSE), evaluateSpeiße(HAUPTSPEISSE), evaluateSpeiße(NACHSPEISSE));
    }

    private KategorieEntity evaluateSpeiße(String name) {
        Optional<KategorieEntity> speiße = kategorieRepository.findByName(name);
        if (!speiße.isPresent()) {
            return createEntityAndSave(name);
        }

        return speiße.get();
    }

    private KategorieEntity createEntityAndSave(String name) {
        KategorieEntity kategorieEntity = new KategorieEntity();
        kategorieEntity.setName(name);
        return kategorieRepository.save(kategorieEntity);
    }

    @Override
    public List<KategorieEntity> getAllByUserName(String username) {
        List<KategorieEntity> allKategories = getAll();
        List<KategorieEntity> kategorieEntities = new ArrayList<>();

        for (KategorieEntity gruppe : allKategories) {
            List<UserEntity> userEntityList = gruppe.getUserEntityList();
            if (userEntityList.stream().anyMatch(user -> user.getUsername().equals(username))) {
                kategorieEntities.add(gruppe);
            }
        }

        return kategorieEntities;
    }

}
