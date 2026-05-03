package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Record;
import com.svalero.swimhub.exception.RecordNotFoundException;
import com.svalero.swimhub.repository.RecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

        when(recordRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(r1)));

        Page<Record> result = recordService.findAll(null, null, null, null, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        verify(recordRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void findAll_filterByGender_returnsList() {
        Record r1 = new Record();
        r1.setId(1L);
        r1.setGender("M");

        when(recordRepository.findByGender(eq("M"), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(r1)));

        Page<Record> result = recordService.findAll("M", null, null, null, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        verify(recordRepository, times(1)).findByGender(eq("M"), any(Pageable.class));
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
