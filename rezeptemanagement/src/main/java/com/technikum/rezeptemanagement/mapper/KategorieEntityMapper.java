package com.technikum.rezeptemanagement.mapper;

import com.technikum.rezeptemanagement.Entity.KategorieEntity;
import com.technikum.rezeptemanagement.Entity.UserEntity;
import com.technikum.rezeptemanagement.Service.user.UserService;
import com.technikum.rezeptemanagement.models.request.KategorieCreateRequest;
import com.technikum.rezeptemanagement.models.request.KategorieUpdateRequest;
import com.technikum.rezeptemanagement.models.response.KategorieResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KategorieEntityMapper {

    private final UserService userService;

    public KategorieEntityMapper(UserService userService) {this.userService = userService;}

    public List<KategorieResponse> mapToKategorieReponseList(List<KategorieEntity> kategorieEntityList) {
        return kategorieEntityList.stream().map(
                entity -> mapToKategorieResponse(entity))
                .collect(Collectors.toList());

    }

    public KategorieResponse mapToKategorieResponse(KategorieEntity entity) {
        return new KategorieResponse(entity.getId().toString(), entity.getName());
    }

    public KategorieEntity mapFromCreateRequest(KategorieCreateRequest kategorieCreateRequest) {
        KategorieEntity kategorieEntity = new KategorieEntity();
        kategorieEntity.setName(kategorieCreateRequest.getName());
        return kategorieEntity;
    }

    public KategorieEntity mapFromUpdateRequest(KategorieUpdateRequest kategorieCreateRequest) {
        List<UserEntity> users = kategorieCreateRequest.getUsernames().stream().
                map(userService::findByUserName).collect(Collectors.toList());

        KategorieEntity kategorieEntity = new KategorieEntity();
        kategorieEntity.setName(kategorieCreateRequest.getName());
        kategorieEntity.setUserEntityList(users);
        return kategorieEntity;
    }
}
