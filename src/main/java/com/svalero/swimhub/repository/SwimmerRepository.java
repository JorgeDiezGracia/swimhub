package com.svalero.swimhub.repository;

import com.svalero.swimhub.domain.Swimmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SwimmerRepository extends JpaRepository<Swimmer, Long> {

    List<Swimmer> findByGender(String gender);
    List<Swimmer> findByCategoryId(Long categoryId);
    List<Swimmer> findByClubId(Long clubId);
    List<Swimmer> findByClubLeagueFederationId(Long federationId);
    List<Swimmer> findByGenderAndCategoryId(String gender, Long categoryId);
}
