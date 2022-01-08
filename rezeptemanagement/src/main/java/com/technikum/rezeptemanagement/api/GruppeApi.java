package com.technikum.rezeptemanagement.api;

import com.technikum.rezeptemanagement.Entity.GruppeEntity;
import com.technikum.rezeptemanagement.Entity.RezeptEntity;
import com.technikum.rezeptemanagement.Entity.UserEntity;
import com.technikum.rezeptemanagement.Service.gruppe.GruppeService;
import com.technikum.rezeptemanagement.Service.user.UserService;
import com.technikum.rezeptemanagement.mapper.GruppeEntityMapper;
import com.technikum.rezeptemanagement.mapper.RezepteMapper;
import com.technikum.rezeptemanagement.models.request.GruppeCreateRequest;
import com.technikum.rezeptemanagement.models.request.GruppeCreateRequestUpdate;
import com.technikum.rezeptemanagement.models.response.GruppeResponse;
import com.technikum.rezeptemanagement.models.response.RezeptResponse;
import com.technikum.rezeptemanagement.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/gruppe")
public class GruppeApi {

    private final GruppeService gruppeService;

    private final GruppeEntityMapper mapper;

    private final RezepteMapper rezepteMapper;

    private final UserService userService;

    JwtUtil jwtUtil = new JwtUtil();

    public GruppeApi(GruppeService gruppeService, GruppeEntityMapper mapper, RezepteMapper rezepteMapper, UserService userService) {
        this.gruppeService = gruppeService;
        this.mapper = mapper;
        this.rezepteMapper = rezepteMapper;
        this.userService = userService;
    }

    @GetMapping
    public List<GruppeResponse> getAllGroups(@RequestHeader(value = "Authorization") String authorizationHeader) {
        List<GruppeEntity> allGroups = gruppeService.getAllByUserName(extractUserNameFromJwt(authorizationHeader));

        return mapper.mapToGruppeResponeList(allGroups);
    }

    @PostMapping("/create")
    public String addGruppe(@RequestHeader(value = "Authorization") String authorizationHeader,
                            @RequestBody GruppeCreateRequest gruppeCreateRequest) {
        GruppeEntity gruppeEntity = mapper.mapFromRequestBasic(gruppeCreateRequest);
        UserEntity user = userService.findByUserName(extractUserNameFromJwt(authorizationHeader));
        gruppeEntity.setUserEntityList(List.of(user));

        GruppeEntity savedGruppe = gruppeService.save(gruppeEntity);

        return savedGruppe.getId().toString();
    }

    @PutMapping("/{id}")
    public GruppeResponse update(@PathVariable String id, @RequestBody GruppeCreateRequestUpdate request) {
        GruppeEntity entity = mapper.mapFromRequestUpdate(request);
        entity.setId(UUID.fromString(id));

        GruppeEntity savedEntity = gruppeService.save(entity);

        return mapper.mapGruppeResponse(savedEntity);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        gruppeService.deleteById(UUID.fromString(id));

        return id;
    }

    @GetMapping("/group/{id}/rezepte")
    public List<RezeptResponse> getRezepteByGroupId(@PathVariable String id) {
        List<RezeptEntity> allRezepteByGroupId = gruppeService.findAllRezepteById(UUID.fromString(id));

        return rezepteMapper.mapToRezepteReponseList(allRezepteByGroupId);
    }

    private String extractUserNameFromJwt(@RequestHeader("Authorization") String authorizationHeader) {
        return jwtUtil.extractUsername(authorizationHeader.split(" ")[1]);
    }
}
