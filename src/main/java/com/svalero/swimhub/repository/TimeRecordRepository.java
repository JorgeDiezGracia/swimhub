package com.svalero.swimhub.repository;

import com.svalero.swimhub.domain.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Long> {

    List<TimeRecord> findBySwimmerId(Long swimmerId);
    List<TimeRecord> findByRaceId(Long raceId);
    List<TimeRecord> findByEventId(Long eventId);
    List<TimeRecord> findBySwimmerIdAndRaceId(Long swimmerId, Long raceId);
}
