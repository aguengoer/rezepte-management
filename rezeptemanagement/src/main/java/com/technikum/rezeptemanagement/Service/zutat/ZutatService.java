package com.technikum.rezeptemanagement.Service.zutat;

import com.technikum.rezeptemanagement.Entity.ZutatEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ZutatService {

    ZutatEntity save(ZutatEntity zutatEntity);

    List<ZutatEntity> getAll();

    ZutatEntity findById(UUID id);

    String deleteUserById(UUID id);
}
