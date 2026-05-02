package com.svalero.swimhub.repository;

import com.svalero.swimhub.domain.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    Page<Club> findAll(Pageable pageable);
    Page<Club> findByLeagueFederationId(Long federationId, Pageable pageable);
    List<Club> findByLeagueFederationId(Long federationId);
}