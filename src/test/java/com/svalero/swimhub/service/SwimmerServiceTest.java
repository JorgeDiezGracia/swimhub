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

        when(swimmerRepository.findAll()).thenReturn(List.of(s1));

        List<Swimmer> result = swimmerService.findAll(null, null, null, null);

        assertEquals(1, result.size());
        verify(swimmerRepository, times(1)).findAll();
    }

    @Test
    void findAll_filterByGender_returnsList() {
        Swimmer s1 = new Swimmer();
        s1.setId(1L);
        s1.setName("Carlos");
        s1.setGender("M");

        when(swimmerRepository.findByGender("M")).thenReturn(List.of(s1));

        List<Swimmer> result = swimmerService.findAll("M", null, null, null);

        assertEquals(1, result.size());
        assertEquals("M", result.get(0).getGender());
        verify(swimmerRepository, times(1)).findByGender("M");
    }

    @Test
    void findAll_filterByGenderAndCategory_returnsList() {
        Swimmer s1 = new Swimmer();
        s1.setId(1L);
        s1.setName("Carlos");
        s1.setGender("M");

        when(swimmerRepository.findByGenderAndCategoryId("M", 4L)).thenReturn(List.of(s1));

        List<Swimmer> result = swimmerService.findAll("M", 4L, null, null);

        assertEquals(1, result.size());
        verify(swimmerRepository, times(1)).findByGenderAndCategoryId("M", 4L);
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
