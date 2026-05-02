package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Swimmer;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.repository.SwimmerRepository;
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
import static org.mockito.ArgumentMatchers.eq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class SwimmerServiceTest {

    @Mock
    private SwimmerRepository swimmerRepository;

    @InjectMocks
    private SwimmerService swimmerService;

    @Test
    void findAll_noFilters_returnsList() {
        Swimmer s1 = new Swimmer();
        s1.setId(1L);
        s1.setName("Carlos");
        s1.setGender("M");

        when(swimmerRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(s1)));

        Page<Swimmer> result = swimmerService.findAll(null, null, null, null, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        verify(swimmerRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void findAll_filterByGender_returnsList() {
        Swimmer s1 = new Swimmer();
        s1.setId(1L);
        s1.setName("Carlos");
        s1.setGender("M");

        when(swimmerRepository.findByGender(eq("M"), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(s1)));

        Page<Swimmer> result = swimmerService.findAll("M", null, null, null, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        verify(swimmerRepository, times(1)).findByGender(eq("M"), any(Pageable.class));
    }

    @Test
    void findAll_filterByGenderAndCategory_returnsList() {
        Swimmer s1 = new Swimmer();
        s1.setId(1L);
        s1.setName("Carlos");
        s1.setGender("M");

        when(swimmerRepository.findByGenderAndCategoryId(eq("M"), eq(4L), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(s1)));

        Page<Swimmer> result = swimmerService.findAll("M", 4L, null, null, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        verify(swimmerRepository, times(1)).findByGenderAndCategoryId(eq("M"), eq(4L), any(Pageable.class));
    }

    @Test
    void findById_existingId_returnsSwimmer() throws SwimmerNotFoundException {
        Swimmer s = new Swimmer();
        s.setId(1L);
        s.setName("Carlos");

        when(swimmerRepository.findById(1L)).thenReturn(Optional.of(s));

        Swimmer result = swimmerService.findById(1L);

        assertEquals("Carlos", result.getName());
        verify(swimmerRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(swimmerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(SwimmerNotFoundException.class,
                () -> swimmerService.findById(99L));
    }
}
