package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Record;
import com.svalero.swimhub.exception.RecordNotFoundException;
import com.svalero.swimhub.repository.RecordRepository;
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
public class RecordServiceTest {

    @Mock
    private RecordRepository recordRepository;

    @InjectMocks
    private RecordService recordService;

    @Test
    void findAll_noFilters_returnsList() {
        Record r1 = new Record();
        r1.setId(1L);
        r1.setTime(27.90);
        r1.setGender("M");

        when(recordRepository.findAll()).thenReturn(List.of(r1));

        List<Record> result = recordService.findAll(null, null, null, null);

        assertEquals(1, result.size());
        verify(recordRepository, times(1)).findAll();
    }

    @Test
    void findAll_filterByGender_returnsList() {
        Record r1 = new Record();
        r1.setId(1L);
        r1.setGender("M");

        when(recordRepository.findByGender("M")).thenReturn(List.of(r1));

        List<Record> result = recordService.findAll("M", null, null, null);

        assertEquals(1, result.size());
        verify(recordRepository, times(1)).findByGender("M");
    }

    @Test
    void findById_existingId_returnsRecord() throws RecordNotFoundException {
        Record r = new Record();
        r.setId(1L);
        r.setTime(27.90);

        when(recordRepository.findById(1L)).thenReturn(Optional.of(r));

        Record result = recordService.findById(1L);

        assertEquals(27.90, result.getTime());
        verify(recordRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(recordRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class,
                () -> recordService.findById(99L));
    }
}
