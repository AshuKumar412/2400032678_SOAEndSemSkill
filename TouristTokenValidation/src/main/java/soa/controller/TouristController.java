package soa.controller;

import soa.service.TouristService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tourists")
@SecurityScheme(
        name = "TokenAuth",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        paramName = "Token"
)
@SecurityRequirement(name = "TokenAuth")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTourist(
            @PathVariable Long id,
            @RequestBody soa.entity.Tourist tourist) {

        return touristService.updateTourist(id, tourist);
    }
}