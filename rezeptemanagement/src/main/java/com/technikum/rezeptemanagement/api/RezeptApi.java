package com.technikum.rezeptemanagement.api;

import com.technikum.rezeptemanagement.Entity.GruppeEntity;
import com.technikum.rezeptemanagement.Entity.RezeptEntity;
import com.technikum.rezeptemanagement.Service.gruppe.GruppeService;
import com.technikum.rezeptemanagement.Service.rezepte.RezepteService;
import com.technikum.rezeptemanagement.mapper.RezepteMapper;
import com.technikum.rezeptemanagement.models.request.RezepteRequest;
import com.technikum.rezeptemanagement.models.request.RezepteToGroupRequest;
import com.technikum.rezeptemanagement.models.response.RezeptResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rezept")
public class RezeptApi {

    private final RezepteService rezepteService;

    private final RezepteMapper mapper;

    private final GruppeService gruppeService;

    public RezeptApi(RezepteService rezepteService, RezepteMapper mapper, GruppeService gruppeService) {
        this.rezepteService = rezepteService;
        this.mapper = mapper;
        this.gruppeService = gruppeService;
    }

    @GetMapping
    public List<RezeptResponse> getAllRezepte() {
        List<RezeptEntity> allRezepte = rezepteService.getAll();

        return mapper.mapToRezepteReponseList(allRezepte);
    }

    @PostMapping("/create")
    public String addRezept(@RequestBody RezepteRequest rezepteRequest) {
        RezeptEntity rezeptEntity = mapper.mapFromRequest(rezepteRequest);

        RezeptEntity savedEntity = rezepteService.save(rezeptEntity);

        return savedEntity.getId().toString();
    }

    @PutMapping("/{id}")
    public RezeptResponse update(@PathVariable String id, @RequestBody RezepteRequest request) {
        RezeptEntity entity = mapper.mapFromRequest(request);
        entity.setId(UUID.fromString(id));

        RezeptEntity savedEntity = rezepteService.save(entity);

        return mapper.mapToRezepteRespone(savedEntity);
    }

    @PutMapping("/rezepteToGroup/{id}")
    public RezeptResponse addToGroup(@PathVariable String id, @RequestBody RezepteToGroupRequest request) {
        GruppeEntity gruppeEntity = gruppeService.findById(UUID.fromString(request.getGruppeId()));

        RezeptEntity rezeptEntity = rezepteService.findById(UUID.fromString(id));
        rezeptEntity.getGruppeEntityList().add(gruppeEntity);

        rezepteService.save(rezeptEntity);

        return mapper.mapToRezepteRespone(rezeptEntity);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        rezepteService.deleteById(UUID.fromString(id));

        return id;
    }
}
