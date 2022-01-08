package com.technikum.rezeptemanagement.Service.zutat;

import com.technikum.rezeptemanagement.Entity.ZutatEntity;
import com.technikum.rezeptemanagement.Repository.ZutateRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ZutatServiceImpl implements ZutatService {

    private final ZutateRepository zutateRepository;

    public ZutatServiceImpl(ZutateRepository zutateRepository) {this.zutateRepository = zutateRepository;}

    @Override
    public ZutatEntity save(ZutatEntity zutatEntity) {
        return zutateRepository.save(zutatEntity);
    }

    @Override
    public List<ZutatEntity> getAll() {
        return zutateRepository.findAll();
    }

    @Override
    public ZutatEntity findById(UUID id) {
        Optional<ZutatEntity> byId = zutateRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get();
        }

        throw new IllegalArgumentException(String.format("Zutat %s is not available", id));
    }

    @Override
    public String deleteUserById(UUID id) {
        zutateRepository.deleteById(id);

        return id.toString();
    }
}
