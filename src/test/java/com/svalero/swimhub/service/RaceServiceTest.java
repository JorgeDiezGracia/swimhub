package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Race;
import com.svalero.swimhub.exception.RaceNotFoundException;
import com.svalero.swimhub.repository.RaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RaceServiceTest {

    @Mock
    private RaceRepository raceRepository;

    @InjectMocks
    private RaceService raceService;

    @Test
    void findAll_returnsList() {
        Race r1 = new Race();
        r1.setId(1L);
        r1.setStyle("Libre");
        r1.setDistance(50);

        when(raceRepository.findAll()).thenReturn(List.of(r1));

        List<Race> result = raceService.findAll();

        assertEquals(1, result.size());
        assertEquals("Libre", result.get(0).getStyle());
        verify(raceRepository, times(1)).findAll();
    }

    @Test
    void findById_existingId_returnsRace() throws RaceNotFoundException {
        Race r = new Race();
        r.setId(1L);
        r.setStyle("Libre");

        when(raceRepository.findById(1L)).thenReturn(Optional.of(r));

        Race result = raceService.findById(1L);

        assertEquals("Libre", result.getStyle());
        verify(raceRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(raceRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RaceNotFoundException.class,
                () -> raceService.findById(99L));
    }
}
