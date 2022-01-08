package com.technikum.rezeptemanagement.api;


import com.technikum.rezeptemanagement.Entity.KategorieEntity;
import com.technikum.rezeptemanagement.Entity.UserEntity;
import com.technikum.rezeptemanagement.Service.kategorie.KategorieService;
import com.technikum.rezeptemanagement.Service.user.UserService;
import com.technikum.rezeptemanagement.mapper.KategorieEntityMapper;
import com.technikum.rezeptemanagement.models.request.KategorieCreateRequest;
import com.technikum.rezeptemanagement.models.request.KategorieUpdateRequest;
import com.technikum.rezeptemanagement.models.response.KategorieResponse;
import com.technikum.rezeptemanagement.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/kategorie")
public class KategorieApi {

    private final KategorieService kategorieService;

    private final KategorieEntityMapper mapper;

    private final UserService userService;

    JwtUtil jwtUtil = new JwtUtil();

    public KategorieApi(KategorieService kategorieService, KategorieEntityMapper kategorieEntityMapper, UserService userService) {
        this.kategorieService = kategorieService;
        this.mapper = kategorieEntityMapper;
        this.userService = userService;
    }

    @GetMapping
    public List<KategorieResponse> getAllKategories(@RequestHeader(value = "Authorization") String authorizationHeader) {
        List<KategorieEntity> allKategorie = kategorieService.getAllByUserName(extractUserNameFromJwt(authorizationHeader));

        return mapper.mapToKategorieReponseList(allKategorie);
    }

    @PostMapping("/create")
    public String addKategorie(@RequestHeader(value = "Authorization") String authorizationHeader,
                               @RequestBody KategorieCreateRequest kategorieCreateRequest) {
        KategorieEntity kategorieEntity = mapper.mapFromCreateRequest(kategorieCreateRequest);
        UserEntity userEntity = userService.findByUserName(extractUserNameFromJwt(authorizationHeader));
        kategorieEntity.setUserEntityList(List.of(userEntity));

        KategorieEntity savedEntity = kategorieService.save(kategorieEntity);

        return savedEntity.getId().toString();
    }

    @PutMapping("/{id}")
    public KategorieResponse update(@PathVariable String id, @RequestBody KategorieUpdateRequest request) {
        KategorieEntity entity = mapper.mapFromUpdateRequest(request);
        entity.setId(UUID.fromString(id));

        KategorieEntity savedEntity = kategorieService.save(entity);

        return mapper.mapToKategorieResponse(savedEntity);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        kategorieService.deleteById(UUID.fromString(id));

        return id;
    }

    private String extractUserNameFromJwt(@RequestHeader("Authorization") String authorizationHeader) {
        return jwtUtil.extractUsername(authorizationHeader.split(" ")[1]);
    }
}
