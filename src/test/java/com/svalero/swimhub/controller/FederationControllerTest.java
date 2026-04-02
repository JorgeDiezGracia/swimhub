package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Federation;
import com.svalero.swimhub.exception.FederationNotFoundException;
import com.svalero.swimhub.service.FederationService;
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
public class FederationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FederationService federationService;

    @Test
    @WithMockUser
    void findAll_returnsOk() throws Exception {
        Federation f1 = new Federation();
        f1.setId(1L);
        f1.setName("Federación Aragonesa de Natación");

        when(federationService.findAll()).thenReturn(List.of(f1));

        mockMvc.perform(get("/api/federations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Federación Aragonesa de Natación"));
    }

    @Test
    @WithMockUser
    void findById_existingId_returnsOk() throws Exception {
        Federation f = new Federation();
        f.setId(1L);
        f.setName("Federación Aragonesa de Natación");

        when(federationService.findById(1L)).thenReturn(f);

        mockMvc.perform(get("/api/federations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Federación Aragonesa de Natación"));
    }

    @Test
    @WithMockUser
    void findById_nonExistingId_returns404() throws Exception {
        when(federationService.findById(99L))
                .thenThrow(new FederationNotFoundException("Federation not found with id: 99"));

        mockMvc.perform(get("/api/federations/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void findAll_withoutToken_returns403() throws Exception {
        mockMvc.perform(get("/api/federations"))
                .andExpect(status().isForbidden());
    }
}