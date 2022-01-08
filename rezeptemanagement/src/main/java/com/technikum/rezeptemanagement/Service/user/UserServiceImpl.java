package com.technikum.rezeptemanagement.Service.user;

import com.technikum.rezeptemanagement.Entity.UserEntity;
import com.technikum.rezeptemanagement.Exception.UserNotFoundExcpetion;
import com.technikum.rezeptemanagement.Repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity findById(UUID id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            return userEntity.get();
        }

        throw new IllegalArgumentException(String.format("User %s is not available", id));
    }

    @Override
    public UserEntity update(UserEntity user) {
        return userRepository.save(user);
    }


    @Override
    public UserEntity findByUserName(String userName) {
        UserEntity user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UserNotFoundExcpetion("User % does not exists", userName);
        }

        return user;
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUserById(UUID id) {
        userRepository.deleteById(id);

        return id.toString();
    }
}
