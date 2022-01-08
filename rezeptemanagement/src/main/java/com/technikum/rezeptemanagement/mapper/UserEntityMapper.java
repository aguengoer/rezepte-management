package com.technikum.rezeptemanagement.mapper;

import com.technikum.rezeptemanagement.Entity.UserEntity;
import com.technikum.rezeptemanagement.models.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityMapper {

    public List<UserResponse> mapToUserResponseList(List<UserEntity> allUser) {
        return allUser.stream().map(user -> mapToUserResponse(user)).collect(Collectors.toList());
    }

    public UserResponse mapToUserResponse(UserEntity userEntity) {
        return new UserResponse(userEntity.getId().toString(), userEntity.getName(), userEntity.getEmail(), userEntity.getUsername());
    }
}
