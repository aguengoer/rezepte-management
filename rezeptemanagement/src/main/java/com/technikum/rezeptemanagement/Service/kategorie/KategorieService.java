package com.technikum.rezeptemanagement.Service.kategorie;

import com.technikum.rezeptemanagement.Entity.KategorieEntity;
import com.technikum.rezeptemanagement.Entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface KategorieService {

    KategorieEntity save(KategorieEntity kategorieEntity);

    List<KategorieEntity> getAll();

    KategorieEntity findById(UUID id);

    String deleteById(UUID fromString);

    void createDefaultKategoriesForUser(UserEntity userEntity);

    List<KategorieEntity> getAllByUserName(String username);
}
