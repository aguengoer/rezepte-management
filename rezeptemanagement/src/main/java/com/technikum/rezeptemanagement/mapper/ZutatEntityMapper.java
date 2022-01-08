package com.technikum.rezeptemanagement.mapper;

import com.technikum.rezeptemanagement.Entity.ZutatEntity;
import com.technikum.rezeptemanagement.models.request.ZutatRequest;
import com.technikum.rezeptemanagement.models.response.ZutatResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ZutatEntityMapper {

    public List<ZutatResponse> mapToZutateReponseList(List<ZutatEntity> zutatEntities) {
        return zutatEntities.stream().map(
                entity -> mapToZutateResponse(entity))
                .collect(Collectors.toList());

    }

    public ZutatResponse mapToZutateResponse(ZutatEntity entity) {
        return new ZutatResponse(entity.getId().toString(), entity.getName());
    }

    public ZutatEntity mapFromRequest(ZutatRequest zutatRequest) {
        ZutatEntity zutatEntity = new ZutatEntity();
        zutatEntity.setName(zutatRequest.getName());
        return zutatEntity;
    }
}
