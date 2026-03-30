package com.svalero.swimhub.repository;

import com.svalero.swimhub.entity.Swimmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwimmerRepository extends JpaRepository<Swimmer, Long> {
}
