package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Event;
import com.svalero.swimhub.exception.EventNotFoundException;
import com.svalero.swimhub.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void findAll_noFilters_returnsList() {
        Event e1 = new Event();
        e1.setId(1L);
        e1.setName("Campeonato Aragonés 2025");

        when(eventRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(e1)));

        Page<Event> result = eventService.findAll(null, null, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        verify(eventRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void findAll_filterByFederation_returnsList() {
        Event e1 = new Event();
        e1.setId(1L);
        e1.setName("Campeonato Aragonés 2025");

        when(eventRepository.findByFederationId(eq(1L), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(e1)));

        Page<Event> result = eventService.findAll(1L, null, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        verify(eventRepository, times(1)).findByFederationId(eq(1L), any(Pageable.class));
    }

    @Test
    void findById_existingId_returnsEvent() throws EventNotFoundException {
        Event e = new Event();
        e.setId(1L);
        e.setName("Campeonato Aragonés 2025");

        when(eventRepository.findById(1L)).thenReturn(Optional.of(e));

        Event result = eventService.findById(1L);

        assertEquals("Campeonato Aragonés 2025", result.getName());
        verify(eventRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(eventRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EventNotFoundException.class,
                () -> eventService.findById(99L));
    }
}
