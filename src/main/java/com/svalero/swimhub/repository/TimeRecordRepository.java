package com.svalero.swimhub.repository;

import com.svalero.swimhub.domain.TimeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Long> {

    Page<TimeRecord> findAll(Pageable pageable);
    Page<TimeRecord> findBySwimmerId(Long swimmerId, Pageable pageable);
    Page<TimeRecord> findByRaceId(Long raceId, Pageable pageable);
    Page<TimeRecord> findByEventId(Long eventId, Pageable pageable);
    Page<TimeRecord> findBySwimmerIdAndRaceId(Long swimmerId, Long raceId, Pageable pageable);
}
