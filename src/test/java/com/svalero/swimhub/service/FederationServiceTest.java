package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Federation;
import com.svalero.swimhub.exception.FederationNotFoundException;
import com.svalero.swimhub.repository.FederationRepository;
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
public class FederationServiceTest {

    @Mock
    private FederationRepository federationRepository;

    @InjectMocks
    private FederationService federationService;

    @Test
    void findAll_returnsList() {
        Federation f1 = new Federation();
        f1.setId(1L);
        f1.setName("Federación Aragonesa de Natación");

        when(federationRepository.findAll()).thenReturn(List.of(f1));

        List<Federation> result = federationService.findAll();

        assertEquals(1, result.size());
        assertEquals("Federación Aragonesa de Natación", result.get(0).getName());
        verify(federationRepository, times(1)).findAll();
    }

    @Test
    void findById_existingId_returnsFederation() throws FederationNotFoundException {
        Federation f = new Federation();
        f.setId(1L);
        f.setName("Federación Aragonesa de Natación");

        when(federationRepository.findById(1L)).thenReturn(Optional.of(f));

        Federation result = federationService.findById(1L);

        assertEquals("Federación Aragonesa de Natación", result.getName());
        verify(federationRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(federationRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(FederationNotFoundException.class,
                () -> federationService.findById(99L));
    }
}
