package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Record;
import com.svalero.swimhub.exception.RecordNotFoundException;
import com.svalero.swimhub.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordService recordService;

    @Test
    @WithMockUser
    void findAll_returnsOk() throws Exception {
        Record r1 = new Record();
        r1.setId(1L);
        r1.setTime(27.90);
        r1.setGender("M");

        when(recordService.findAll(null, null, null, null, PageRequest.of(0, 10, Sort.by("id"))))
                .thenReturn(new PageImpl<>(List.of(r1)));

        mockMvc.perform(get("/api/records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].time").value(27.90));
    }

    @Test
    @WithMockUser
    void findById_existingId_returnsOk() throws Exception {
        Record r = new Record();
        r.setId(1L);
        r.setTime(27.90);

        when(recordService.findById(1L)).thenReturn(r);

        mockMvc.perform(get("/api/records/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.time").value(27.90));
    }

    @Test
    @WithMockUser
    void findById_nonExistingId_returns404() throws Exception {
        when(recordService.findById(99L))
                .thenThrow(new RecordNotFoundException("Record not found with id: 99"));

        mockMvc.perform(get("/api/records/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void findAll_withoutToken_returns403() throws Exception {
        mockMvc.perform(get("/api/records"))
                .andExpect(status().isForbidden());
    }
}
