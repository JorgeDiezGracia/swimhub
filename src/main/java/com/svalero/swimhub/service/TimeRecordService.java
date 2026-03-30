package com.svalero.swimhub.service;

import com.svalero.swimhub.entity.TimeRecord;
import com.svalero.swimhub.repository.TimeRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeRecordService {

    private final TimeRecordRepository timeRecordRepository;

    public List<TimeRecord> findAll() {
        return timeRecordRepository.findAll();
    }

    public TimeRecord findById(Long id) {
        return timeRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TimeRecord not found with id: " + id));
    }
}
