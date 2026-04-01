package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Event;
import com.svalero.swimhub.exception.EventNotFoundException;
import com.svalero.swimhub.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> findAll(Long federationId, String poolType) {
        if (federationId != null) {
            return eventRepository.findByFederationId(federationId);
        } else if (poolType != null) {
            return eventRepository.findByPoolType(poolType);
        }
        return eventRepository.findAll();
    }

    public Event findById(Long id) throws EventNotFoundException {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(
                        "Event not found with id: " + id));
    }
}
