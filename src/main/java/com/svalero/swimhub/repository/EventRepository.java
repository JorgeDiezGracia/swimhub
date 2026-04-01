package com.svalero.swimhub.repository;

import com.svalero.swimhub.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByFederationId(Long federationId);
    List<Event> findByPoolType(String poolType);
}
