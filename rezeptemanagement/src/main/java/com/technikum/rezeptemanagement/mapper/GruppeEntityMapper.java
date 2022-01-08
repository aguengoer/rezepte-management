package com.technikum.rezeptemanagement.mapper;

import com.technikum.rezeptemanagement.Entity.GruppeEntity;
import com.technikum.rezeptemanagement.Entity.RezeptEntity;
import com.technikum.rezeptemanagement.Entity.UserEntity;
import com.technikum.rezeptemanagement.Service.rezepte.RezepteService;
import com.technikum.rezeptemanagement.Service.user.UserService;
import com.technikum.rezeptemanagement.models.request.GruppeCreateRequest;
import com.technikum.rezeptemanagement.models.request.GruppeCreateRequestUpdate;
import com.technikum.rezeptemanagement.models.response.GruppeResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GruppeEntityMapper {

    private final UserEntityMapper userEntityMapper;

    private final UserService userService;

    private final RezepteService rezepteService;

    public GruppeEntityMapper(UserEntityMapper userEntityMapper, UserService userService, RezepteService rezepteService) {
        this.userEntityMapper = userEntityMapper;
        this.userService = userService;
        this.rezepteService = rezepteService;
    }

    public List<GruppeResponse> mapToGruppeResponeList(List<GruppeEntity> gruppeEntities) {
        return gruppeEntities.stream().map(gruppe -> mapGruppeResponse(gruppe))
                .collect(Collectors.toList());
    }

    public GruppeResponse mapGruppeResponse(GruppeEntity gruppe) {
        return new GruppeResponse(gruppe.getId().toString(), gruppe.getName(),
                userEntityMapper.mapToUserResponseList(gruppe.getUserEntityList()));
    }

    public GruppeEntity mapFromRequestUpdate(GruppeCreateRequestUpdate gruppeRequest) {
        List<UserEntity> users = gruppeRequest.getUsernames().stream().
                map(userService::findByUserName).collect(Collectors.toList());


        List<RezeptEntity> rezeptEntities = gruppeRequest.getRezeptIds().stream()
                .map(rezeptId -> rezepteService.findById(UUID.fromString(rezeptId)))
                .collect(Collectors.toList());


        GruppeEntity gruppeEntity = new GruppeEntity();
        gruppeEntity.setName(gruppeRequest.getName());
        gruppeEntity.setUserEntityList(users);
        gruppeEntity.setRezeptEntityList(rezeptEntities);

        return gruppeEntity;

    }

    public GruppeEntity mapFromRequestBasic(GruppeCreateRequest gruppeCreateRequest) {
        GruppeEntity gruppeEntity = new GruppeEntity();
        gruppeEntity.setName(gruppeCreateRequest.getName());

        return gruppeEntity;

    }
}
