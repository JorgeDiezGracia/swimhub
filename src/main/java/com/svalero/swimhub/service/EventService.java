package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Event;
import com.svalero.swimhub.exception.EventNotFoundException;
import com.svalero.swimhub.exception.FederationNotFoundException;
import com.svalero.swimhub.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Page<Event> findAll(Long federationId, String poolType, Pageable pageable) {
        if (federationId != null) {
            return eventRepository.findByFederationId(federationId, pageable);
        } else if (poolType != null) {
            return eventRepository.findByPoolType(poolType, pageable);
        }
        return eventRepository.findAll(pageable);
    }

    public Event findById(Long id) throws EventNotFoundException {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(
                        "Event not found with id: " + id));
    }

    public List<Event> findByFederationId(Long federationId) throws FederationNotFoundException {
        return eventRepository.findByFederationId(federationId);
    }
}
