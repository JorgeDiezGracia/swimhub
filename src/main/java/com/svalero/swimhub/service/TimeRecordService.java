package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.TimeRecord;
import com.svalero.swimhub.exception.EventNotFoundException;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.exception.TimeRecordNotFoundException;
import com.svalero.swimhub.repository.TimeRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeRecordService {

    private final TimeRecordRepository timeRecordRepository;

    public List<TimeRecord> findAll(Long swimmerId, Long raceId, Long eventId) {
        if (swimmerId != null && raceId != null) {
            return timeRecordRepository.findBySwimmerIdAndRaceId(swimmerId, raceId);
        } else if (swimmerId != null) {
            return timeRecordRepository.findBySwimmerId(swimmerId);
        } else if (raceId != null) {
            return timeRecordRepository.findByRaceId(raceId);
        } else if (eventId != null) {
            return timeRecordRepository.findByEventId(eventId);
        }
        return timeRecordRepository.findAll();
    }

    public TimeRecord findById(Long id) throws TimeRecordNotFoundException {
        return timeRecordRepository.findById(id)
                .orElseThrow(() -> new TimeRecordNotFoundException(
                        "TimeRecord not found with id: " + id));
    }

    public List<TimeRecord> findBySwimmerId(Long swimmerId) throws SwimmerNotFoundException {
        return timeRecordRepository.findBySwimmerId(swimmerId);
    }

    public List<TimeRecord> findByEventId(Long eventId) throws EventNotFoundException {
        return timeRecordRepository.findByEventId(eventId);
    }
}
