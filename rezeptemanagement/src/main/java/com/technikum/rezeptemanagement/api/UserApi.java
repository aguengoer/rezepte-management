package com.technikum.rezeptemanagement.api;

import com.technikum.rezeptemanagement.Entity.UserEntity;
import com.technikum.rezeptemanagement.Service.user.UserService;
import com.technikum.rezeptemanagement.mapper.UserEntityMapper;
import com.technikum.rezeptemanagement.models.request.UserRegistrationRequest;
import com.technikum.rezeptemanagement.models.response.UserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserApi {

    private final UserService userService;

    private final UserEntityMapper mapper;

    public UserApi(UserService userService, UserEntityMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UserResponse> getAllUser() {
        List<UserEntity> allUser = userService.getAll();

        return mapper.mapToUserResponseList(allUser);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        UserEntity userById = userService.findById(UUID.fromString(id));

        return mapper.mapToUserResponse(userById);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable String id, @RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserEntity userEntity = mapUserEntityFromRequest(userRegistrationRequest);
        userEntity.setId(UUID.fromString(id));

        return userService.save(userEntity);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUserById(UUID.fromString(id));

        return id;
    }

    private UserEntity mapUserEntityFromRequest(UserRegistrationRequest userRegistrationRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRegistrationRequest.getEmail());
        userEntity.setUsername(userRegistrationRequest.getUsername());
        userEntity.setPassword(userRegistrationRequest.getPassword());
        userEntity.setName(userRegistrationRequest.getName());

        return userEntity;
    }

}
