package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Swimmer;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.service.SwimmerService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SwimmerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SwimmerService swimmerService;

    @Test
    @WithMockUser
    void findAll_returnsOk() throws Exception {
        Swimmer s1 = new Swimmer();
        s1.setId(1L);
        s1.setName("Carlos");
        s1.setSurname("García");

        when(swimmerService.findAll(null, null, null, null, PageRequest.of(0, 10, Sort.by("id"))))
                .thenReturn(new PageImpl<>(List.of(s1)));
        mockMvc.perform(get("/api/swimmers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Carlos"));
    }

    @Test
    @WithMockUser
    void findAll_filterByGender_returnsOk() throws Exception {
        Swimmer s1 = new Swimmer();
        s1.setId(1L);
        s1.setName("Carlos");
        s1.setGender("M");

        when(swimmerService.findAll(eq("M"), isNull(), isNull(), isNull(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(s1)));

        mockMvc.perform(get("/api/swimmers?gender=M"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].gender").value("M"));
    }

    @Test
    @WithMockUser
    void findById_existingId_returnsOk() throws Exception {
        Swimmer s = new Swimmer();
        s.setId(1L);
        s.setName("Carlos");

        when(swimmerService.findById(1L)).thenReturn(s);

        mockMvc.perform(get("/api/swimmers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Carlos"));
    }

    @Test
    @WithMockUser
    void findById_nonExistingId_returns404() throws Exception {
        when(swimmerService.findById(99L))
                .thenThrow(new SwimmerNotFoundException("Swimmer not found with id: 99"));

        mockMvc.perform(get("/api/swimmers/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void findAll_withoutToken_returns403() throws Exception {
        mockMvc.perform(get("/api/swimmers"))
                .andExpect(status().isForbidden());
    }
}