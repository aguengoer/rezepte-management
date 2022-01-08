package com.technikum.rezeptemanagement.Repository;

import com.technikum.rezeptemanagement.Entity.ZutatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ZutateRepository extends JpaRepository<ZutatEntity, UUID> {
}
