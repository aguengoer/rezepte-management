package com.technikum.rezeptemanagement.Repository;

import com.technikum.rezeptemanagement.Entity.RezeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RezeptRepository extends JpaRepository<RezeptEntity, UUID> {

}
