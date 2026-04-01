package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.TimeRecord;
import com.svalero.swimhub.exception.TimeRecordNotFoundException;
import com.svalero.swimhub.repository.TimeRecordRepository;
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
public class TimeRecordServiceTest {

    @Mock
    private TimeRecordRepository timeRecordRepository;

    @InjectMocks
    private TimeRecordService timeRecordService;

    @Test
    void findAll_noFilters_returnsList() {
        TimeRecord tr1 = new TimeRecord();
        tr1.setId(1L);
        tr1.setTime(28.45);

        when(timeRecordRepository.findAll()).thenReturn(List.of(tr1));

        List<TimeRecord> result = timeRecordService.findAll(null, null, null);

        assertEquals(1, result.size());
        verify(timeRecordRepository, times(1)).findAll();
    }

    @Test
    void findAll_filterBySwimmer_returnsList() {
        TimeRecord tr1 = new TimeRecord();
        tr1.setId(1L);
        tr1.setTime(28.45);

        when(timeRecordRepository.findBySwimmerId(1L)).thenReturn(List.of(tr1));

        List<TimeRecord> result = timeRecordService.findAll(1L, null, null);

        assertEquals(1, result.size());
        verify(timeRecordRepository, times(1)).findBySwimmerId(1L);
    }

    @Test
    void findById_existingId_returnsTimeRecord() throws TimeRecordNotFoundException {
        TimeRecord tr = new TimeRecord();
        tr.setId(1L);
        tr.setTime(28.45);

        when(timeRecordRepository.findById(1L)).thenReturn(Optional.of(tr));

        TimeRecord result = timeRecordService.findById(1L);

        assertEquals(28.45, result.getTime());
        verify(timeRecordRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(timeRecordRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TimeRecordNotFoundException.class,
                () -> timeRecordService.findById(99L));
    }
}
