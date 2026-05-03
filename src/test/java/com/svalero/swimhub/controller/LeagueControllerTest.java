package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.League;
import com.svalero.swimhub.exception.LeagueNotFoundException;
import com.svalero.swimhub.service.LeagueService;
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

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;

@SpringBootTest
@AutoConfigureMockMvc
public class LeagueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeagueService leagueService;

    @Test
    @WithMockUser
    void findAll_returnsOk() throws Exception {
        League l1 = new League();
        l1.setId(1L);
        l1.setName("Liga Zaragoza");

        when(leagueService.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(l1)));

        mockMvc.perform(get("/api/leagues"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Liga Zaragoza"));
    }

    @Test
    @WithMockUser
    void findById_existingId_returnsOk() throws Exception {
        League l = new League();
        l.setId(1L);
        l.setName("Liga Zaragoza");

        when(leagueService.findById(1L)).thenReturn(l);

        mockMvc.perform(get("/api/leagues/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Liga Zaragoza"));
    }

    @Test
    @WithMockUser
    void findById_nonExistingId_returns404() throws Exception {
        when(leagueService.findById(99L))
                .thenThrow(new LeagueNotFoundException("League not found with id: 99"));

        mockMvc.perform(get("/api/leagues/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void findAll_withoutToken_returns403() throws Exception {
        mockMvc.perform(get("/api/leagues"))
                .andExpect(status().isForbidden());
    }
}