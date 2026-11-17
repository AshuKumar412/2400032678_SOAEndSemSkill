package soa.service;

import soa.entity.Tourist;
import soa.repository.TouristRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TouristService {

    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public ResponseEntity<?> updateTourist(
            Long id, Tourist updatedTourist) {

        Optional<Tourist> optionalTourist =
                touristRepository.findById(id);

        if (optionalTourist.isPresent()) {

            Tourist tourist = optionalTourist.get();

            tourist.setName(updatedTourist.getName());
            tourist.setNationality(updatedTourist.getNationality());
            tourist.setPassportNumber(updatedTourist.getPassportNumber());

            Tourist savedTourist =
                    touristRepository.save(tourist);

            return ResponseEntity.ok(savedTourist);
        }

        Map<String, Object> error = new LinkedHashMap<>();
        error.put("status", 404);
        error.put("error", "Not Found");
        error.put("message", "Tourist not found with ID: " + id);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}