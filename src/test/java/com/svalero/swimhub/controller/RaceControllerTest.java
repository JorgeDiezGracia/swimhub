package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Race;
import com.svalero.swimhub.exception.RaceNotFoundException;
import com.svalero.swimhub.service.RaceService;
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
public class RaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RaceService raceService;

    @Test
    @WithMockUser
    void findAll_returnsOk() throws Exception {
        Race r1 = new Race();
        r1.setId(1L);
        r1.setStyle("Libre");
        r1.setDistance(50);

        when(raceService.findAll()).thenReturn(List.of(r1));

        mockMvc.perform(get("/api/races"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].style").value("Libre"));
    }

    @Test
    @WithMockUser
    void findById_existingId_returnsOk() throws Exception {
        Race r = new Race();
        r.setId(1L);
        r.setStyle("Libre");

        when(raceService.findById(1L)).thenReturn(r);

        mockMvc.perform(get("/api/races/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.style").value("Libre"));
    }

    @Test
    @WithMockUser
    void findById_nonExistingId_returns404() throws Exception {
        when(raceService.findById(99L))
                .thenThrow(new RaceNotFoundException("Race not found with id: 99"));

        mockMvc.perform(get("/api/races/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void findAll_withoutToken_returns403() throws Exception {
        mockMvc.perform(get("/api/races"))
                .andExpect(status().isForbidden());
    }
}
