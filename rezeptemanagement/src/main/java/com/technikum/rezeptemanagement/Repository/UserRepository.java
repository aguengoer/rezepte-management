package com.technikum.rezeptemanagement.Repository;

import com.technikum.rezeptemanagement.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("select user from UserEntity user where user.username = :username")
    UserEntity findByUserName(@Param("username") String userName);
}
