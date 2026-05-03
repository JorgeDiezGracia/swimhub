package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Club;
import com.svalero.swimhub.exception.ClubNotFoundException;
import com.svalero.swimhub.repository.ClubRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.data.domain.Page;

@ExtendWith(MockitoExtension.class)
public class ClubServiceTest {

    @Mock
    private ClubRepository clubRepository;

    @InjectMocks
    private ClubService clubService;

    @Test
    void findAll_returnsList() {
        Club c1 = new Club();
        c1.setId(1L);
        c1.setName("CN Zaragoza");

        when(clubRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(c1)));

        Page<Club> result = clubService.findAll(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals("CN Zaragoza", result.getContent().get(0).getName());
        verify(clubRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void findById_existingId_returnsClub() throws ClubNotFoundException {
        Club c = new Club();
        c.setId(1L);
        c.setName("CN Zaragoza");

        when(clubRepository.findById(1L)).thenReturn(Optional.of(c));

        Club result = clubService.findById(1L);

        assertEquals("CN Zaragoza", result.getName());
        verify(clubRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(clubRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ClubNotFoundException.class,
                () -> clubService.findById(99L));
    }
}