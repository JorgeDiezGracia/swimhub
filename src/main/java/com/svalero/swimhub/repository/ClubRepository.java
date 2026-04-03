package com.svalero.swimhub.repository;

import com.svalero.swimhub.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByLeagueFederationId(Long federationId);
}