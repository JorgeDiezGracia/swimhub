package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Record;
import com.svalero.swimhub.exception.RecordNotFoundException;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public Page<Record> findAll(String gender, Long categoryId, Long federationId, Long raceId, Pageable pageable) {
        if (gender != null && categoryId != null) {
            return recordRepository.findByGenderAndCategoryId(gender, categoryId, pageable);
        } else if (gender != null) {
            return recordRepository.findByGender(gender, pageable);
        } else if (categoryId != null) {
            return recordRepository.findByCategoryId(categoryId, pageable);
        } else if (federationId != null) {
            return recordRepository.findByFederationId(federationId, pageable);
        } else if (raceId != null) {
            return recordRepository.findByRaceId(raceId, pageable);
        }
        return recordRepository.findAll(pageable);
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
