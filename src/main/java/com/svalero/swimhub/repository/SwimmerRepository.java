package com.svalero.swimhub.repository;

import com.svalero.swimhub.domain.Swimmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SwimmerRepository extends JpaRepository<Swimmer, Long> {

    Page<Swimmer> findAll(Pageable pageable);
    Page<Swimmer> findByGender(String gender, Pageable pageable);
    Page<Swimmer> findByCategoryId(Long categoryId, Pageable pageable);
    Page<Swimmer> findByClubId(Long clubId, Pageable pageable);
    Page<Swimmer> findByClubLeagueFederationId(Long federationId, Pageable pageable);
    Page<Swimmer> findByGenderAndCategoryId(String gender, Long categoryId, Pageable pageable);
}
