package com.technikum.rezeptemanagement.mapper;

import com.technikum.rezeptemanagement.Entity.GruppeEntity;
import com.technikum.rezeptemanagement.Entity.RezeptEntity;
import com.technikum.rezeptemanagement.Entity.ZutatEntity;
import com.technikum.rezeptemanagement.Service.gruppe.GruppeService;
import com.technikum.rezeptemanagement.Service.kategorie.KategorieService;
import com.technikum.rezeptemanagement.Service.user.UserService;
import com.technikum.rezeptemanagement.Service.zutat.ZutatService;
import com.technikum.rezeptemanagement.models.request.RezepteRequest;
import com.technikum.rezeptemanagement.models.request.ZutatRequest;
import com.technikum.rezeptemanagement.models.response.RezeptResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RezepteMapper {

    private final UserEntityMapper userEntityMapper;

    private final ZutatEntityMapper zutatEntityMapper;

    private final KategorieEntityMapper kategorieEntityMapper;

    private final GruppeEntityMapper gruppeEntityUpdateMapper;

    private final UserService userService;

    private final ZutatService zutatService;

    private final KategorieService kategorieService;

    private final GruppeService gruppeService;

    public RezepteMapper(UserEntityMapper userEntityMapper, ZutatEntityMapper zutatEntityMapper, KategorieEntityMapper kategorieEntityMapper,
                         GruppeEntityMapper gruppeEntityUpdateMapper, UserService userService,
                         ZutatService zutatService, KategorieService kategorieService,
                         GruppeService gruppeService) {
        this.userEntityMapper = userEntityMapper;
        this.zutatEntityMapper = zutatEntityMapper;
        this.kategorieEntityMapper = kategorieEntityMapper;
        this.gruppeEntityUpdateMapper = gruppeEntityUpdateMapper;
        this.userService = userService;
        this.zutatService = zutatService;
        this.kategorieService = kategorieService;
        this.gruppeService = gruppeService;
    }

    public List<RezeptResponse> mapToRezepteReponseList(List<RezeptEntity> allRezepte) {
        return allRezepte.stream().map(entity -> mapToRezepteRespone(entity)).collect(Collectors.toList());

    }

    public RezeptResponse mapToRezepteRespone(RezeptEntity entity) {
        return new RezeptResponse(entity.getId().toString(), entity.getName(),
                entity.getNotizen(), entity.getDescription(),
                kategorieEntityMapper.mapToKategorieResponse(entity.getKategorieEntity()),
                zutatEntityMapper.mapToZutateReponseList(entity.getZutatEntityList()),
                gruppeEntityUpdateMapper.mapToGruppeResponeList(entity.getGruppeEntityList()));
    }

    public RezeptEntity mapFromRequest(RezepteRequest rezepteRequest) {
        RezeptEntity rezeptEntity = new RezeptEntity();
        rezeptEntity.setDescription(rezepteRequest.getDescription());
        rezeptEntity.setNotizen(rezepteRequest.getNotizen());
        rezeptEntity.setZutatEntityList(createZutate(rezepteRequest.getZutatRequests()));
        rezeptEntity.setKategorieEntity(kategorieService.findById(UUID.fromString(rezepteRequest.getKategorieId())));
        rezeptEntity.setGruppeEntityList(getGruppenEnties(rezepteRequest.getGruppenIds()));
        rezeptEntity.setName(rezepteRequest.getName());

        return rezeptEntity;
    }

    private List<ZutatEntity> createZutate(List<ZutatRequest> zutatRequestList) {
        List<ZutatEntity> zutatEntities = new ArrayList<>();
        for (ZutatRequest request : zutatRequestList) {
            ZutatEntity zutatEntity = new ZutatEntity();
            zutatEntity.setName(request.getName());
            zutatEntities.add(zutatEntity);
            zutatService.save(zutatEntity);
        }
        return zutatEntities;
    }

    private List<GruppeEntity> getGruppenEnties(List<String> gruppenIds) {
        return gruppenIds.stream().map(id -> gruppeService.findById(UUID.fromString(id))).collect(Collectors.toList());
    }
}
