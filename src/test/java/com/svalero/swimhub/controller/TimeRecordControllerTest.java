package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.TimeRecord;
import com.svalero.swimhub.exception.TimeRecordNotFoundException;
import com.svalero.swimhub.service.TimeRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TimeRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeRecordService timeRecordService;

    @Test
    @WithMockUser
    void findAll_returnsOk() throws Exception {
        TimeRecord tr1 = new TimeRecord();
        tr1.setId(1L);
        tr1.setTime(28.45);

        when(timeRecordService.findAll(null, null, null)).thenReturn(List.of(tr1));

        mockMvc.perform(get("/api/time-records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].time").value(28.45));
    }

    @Test
    @WithMockUser
    void findById_existingId_returnsOk() throws Exception {
        TimeRecord tr = new TimeRecord();
        tr.setId(1L);
        tr.setTime(28.45);

        when(timeRecordService.findById(1L)).thenReturn(tr);

        mockMvc.perform(get("/api/time-records/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.time").value(28.45));
    }

    @Test
    @WithMockUser
    void findById_nonExistingId_returns404() throws Exception {
        when(timeRecordService.findById(99L))
                .thenThrow(new TimeRecordNotFoundException("TimeRecord not found with id: 99"));

        mockMvc.perform(get("/api/time-records/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void findAll_withoutToken_returns403() throws Exception {
        mockMvc.perform(get("/api/time-records"))
                .andExpect(status().isForbidden());
    }
}
