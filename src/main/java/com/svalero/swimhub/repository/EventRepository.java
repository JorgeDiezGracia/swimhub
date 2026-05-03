package com.svalero.swimhub.repository;

import com.svalero.swimhub.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAll(Pageable pageable);
    Page<Event> findByFederationId(Long federationId, Pageable pageable);
    Page<Event> findByPoolType(String poolType, Pageable pageable);
    List<Event> findByFederationId(Long federationId);
}
