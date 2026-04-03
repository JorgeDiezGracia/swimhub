package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Record;
import com.svalero.swimhub.exception.RecordNotFoundException;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public List<Record> findAll(String gender, Long categoryId, Long federationId, Long raceId) {
        if (gender != null && categoryId != null) {
            return recordRepository.findByGenderAndCategoryId(gender, categoryId);
        } else if (gender != null) {
            return recordRepository.findByGender(gender);
        } else if (categoryId != null) {
            return recordRepository.findByCategoryId(categoryId);
        } else if (federationId != null) {
            return recordRepository.findByFederationId(federationId);
        } else if (raceId != null) {
            return recordRepository.findByRaceId(raceId);
        }
        return recordRepository.findAll();
    }

    public Record findById(Long id) throws RecordNotFoundException {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(
                        "Record not found with id: " + id));
    }

    public List<Record> findBySwimmerId(Long swimmerId) throws SwimmerNotFoundException {
        return recordRepository.findBySwimmerId(swimmerId);
    }
}
