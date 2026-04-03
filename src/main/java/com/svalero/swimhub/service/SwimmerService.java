package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Swimmer;
import com.svalero.swimhub.exception.ClubNotFoundException;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.repository.SwimmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SwimmerService {

    private final SwimmerRepository swimmerRepository;

    public List<Swimmer> findAll(String gender, Long categoryId, Long clubId, Long federationId) {
        if (gender != null && categoryId != null) {
            return swimmerRepository.findByGenderAndCategoryId(gender, categoryId);
        } else if (gender != null) {
            return swimmerRepository.findByGender(gender);
        } else if (categoryId != null) {
            return swimmerRepository.findByCategoryId(categoryId);
        } else if (clubId != null) {
            return swimmerRepository.findByClubId(clubId);
        } else if (federationId != null) {
            return swimmerRepository.findByClubLeagueFederationId(federationId);
        }
        return swimmerRepository.findAll();
    }

    public Swimmer findById(Long id) throws SwimmerNotFoundException {
        return swimmerRepository.findById(id)
                .orElseThrow(() -> new SwimmerNotFoundException(
                        "Swimmer not found with id: " + id));
    }

    public List<Swimmer> findByClubId(Long clubId) throws ClubNotFoundException {
        return swimmerRepository.findByClubId(clubId);
    }
}