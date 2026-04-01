package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Record;
import com.svalero.swimhub.exception.RecordNotFoundException;
import com.svalero.swimhub.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public Record findById(Long id) throws RecordNotFoundException {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(
                        "Record not found with id: " + id));
    }
}
