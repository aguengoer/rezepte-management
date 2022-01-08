package com.technikum.rezeptemanagement.Service.user;

import com.technikum.rezeptemanagement.Entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface UserService {

    public UserEntity save(UserEntity user);

    public UserEntity findById(UUID id);

    public UserEntity update(UserEntity user);

    public UserEntity findByUserName(String userName);

    public List<UserEntity> getAll();

    public String deleteUserById(UUID id);
}
