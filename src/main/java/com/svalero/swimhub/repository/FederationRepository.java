package com.svalero.swimhub.repository;

import com.svalero.swimhub.entity.Federation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FederationRepository extends JpaRepository<Federation, Long> {
}
