package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.League;
import com.svalero.swimhub.exception.LeagueNotFoundException;
import com.svalero.swimhub.repository.LeagueRepository;
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
public class LeagueServiceTest {

    @Mock
    private LeagueRepository leagueRepository;

    @InjectMocks
    private LeagueService leagueService;

    @Test
    void findAll_returnsList() {
        League l1 = new League();
        l1.setId(1L);
        l1.setName("Liga Zaragoza");

        when(leagueRepository.findAll()).thenReturn(List.of(l1));

        List<League> result = leagueService.findAll();

        assertEquals(1, result.size());
        assertEquals("Liga Zaragoza", result.get(0).getName());
        verify(leagueRepository, times(1)).findAll();
    }

    @Test
    void findById_existingId_returnsLeague() throws LeagueNotFoundException {
        League l = new League();
        l.setId(1L);
        l.setName("Liga Zaragoza");

        when(leagueRepository.findById(1L)).thenReturn(Optional.of(l));

        League result = leagueService.findById(1L);

        assertEquals("Liga Zaragoza", result.getName());
        verify(leagueRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(leagueRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(LeagueNotFoundException.class,
                () -> leagueService.findById(99L));
    }
}
