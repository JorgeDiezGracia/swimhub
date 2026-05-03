package com.svalero.swimhub.repository;

import com.svalero.swimhub.domain.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    Page<Record> findAll(Pageable pageable);
    Page<Record> findByGender(String gender, Pageable pageable);
    Page<Record> findByCategoryId(Long categoryId, Pageable pageable);
    Page<Record> findByFederationId(Long federationId, Pageable pageable);
    Page<Record> findByRaceId(Long raceId, Pageable pageable);
    Page<Record> findByGenderAndCategoryId(String gender, Long categoryId, Pageable pageable);
    List<Record> findBySwimmerId(Long swimmerId);
}
