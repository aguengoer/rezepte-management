package com.technikum.rezeptemanagement.api;

import com.technikum.rezeptemanagement.Entity.GruppeEntity;
import com.technikum.rezeptemanagement.Entity.UserEntity;
import com.technikum.rezeptemanagement.Service.gruppe.GruppeService;
import com.technikum.rezeptemanagement.Service.kategorie.KategorieService;
import com.technikum.rezeptemanagement.Service.user.UserService;
import com.technikum.rezeptemanagement.models.request.UserRegistrationRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationApi {

    private final UserService userService;

    private final GruppeService gruppeService;

    private final KategorieService kategorieService;

    public RegistrationApi(UserService userService, GruppeService gruppeService, KategorieService kategorieService) {
        this.userService = userService;
        this.gruppeService = gruppeService;
        this.kategorieService = kategorieService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserEntity userEntity = mapUserEntityFromRequest(userRegistrationRequest);

        UserEntity savedUser = userService.save(userEntity);
        addUserToDefaultGroup(savedUser);

        kategorieService.createDefaultKategoriesForUser(savedUser);

        return savedUser.getId().toString();
    }

    public void addUserToDefaultGroup(UserEntity savedUser) {
        GruppeEntity gruppeEntity = new GruppeEntity();
        gruppeEntity.setName("Meine Rezepte - " + savedUser.getUsername());
        gruppeEntity.setUserEntityList(List.of(savedUser));
        gruppeService.save(gruppeEntity);
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
