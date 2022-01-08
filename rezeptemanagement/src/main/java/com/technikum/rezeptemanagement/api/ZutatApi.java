package com.technikum.rezeptemanagement.api;


import com.technikum.rezeptemanagement.Entity.ZutatEntity;
import com.technikum.rezeptemanagement.Service.zutat.ZutatService;
import com.technikum.rezeptemanagement.mapper.ZutatEntityMapper;
import com.technikum.rezeptemanagement.models.request.ZutatRequest;
import com.technikum.rezeptemanagement.models.response.ZutatResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/zutat")
public class ZutatApi {

    private final ZutatService zutatService;

    private final ZutatEntityMapper mapper;

    public ZutatApi(ZutatService zutatService, ZutatEntityMapper mapper) {
        this.zutatService = zutatService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ZutatResponse> getAllZutate() {
        List<ZutatEntity> allZutate = zutatService.getAll();

        return mapper.mapToZutateReponseList(allZutate);
    }

    @PostMapping("/create")
    public String addZutat(@RequestBody ZutatRequest zutatRequest) {
        ZutatEntity kategorieEntity = mapper.mapFromRequest(zutatRequest);

        ZutatEntity savedEntity = zutatService.save(kategorieEntity);

        return savedEntity.getId().toString();
    }

    @PutMapping("/{id}")
    public ZutatResponse update(@PathVariable String id, @RequestBody ZutatRequest zutatRequest) {
        ZutatEntity zutatEntity = mapper.mapFromRequest(zutatRequest);
        zutatEntity.setId(UUID.fromString(id));

        ZutatEntity savedEntity = zutatService.save(zutatEntity);

        return mapper.mapToZutateResponse(savedEntity);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        zutatService.deleteUserById(UUID.fromString(id));

        return id;
    }
}
