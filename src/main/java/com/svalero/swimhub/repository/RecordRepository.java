package com.svalero.swimhub.repository;

import com.svalero.swimhub.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByGender(String gender);
    List<Record> findByCategoryId(Long categoryId);
    List<Record> findByFederationId(Long federationId);
    List<Record> findByRaceId(Long raceId);
    List<Record> findByGenderAndCategoryId(String gender, Long categoryId);
    List<Record> findBySwimmerId(Long swimmerId);
}
