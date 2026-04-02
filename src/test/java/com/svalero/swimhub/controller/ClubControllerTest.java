package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Club;
import com.svalero.swimhub.exception.ClubNotFoundException;
import com.svalero.swimhub.service.ClubService;
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
public class ClubControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClubService clubService;

    @Test
    @WithMockUser
    void findAll_returnsOk() throws Exception {
        Club c1 = new Club();
        c1.setId(1L);
        c1.setName("CN Zaragoza");

        when(clubService.findAll()).thenReturn(List.of(c1));

        mockMvc.perform(get("/api/clubs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("CN Zaragoza"));
    }

    @Test
    @WithMockUser
    void findById_existingId_returnsOk() throws Exception {
        Club c = new Club();
        c.setId(1L);
        c.setName("CN Zaragoza");

        when(clubService.findById(1L)).thenReturn(c);

        mockMvc.perform(get("/api/clubs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("CN Zaragoza"));
    }

    @Test
    @WithMockUser
    void findById_nonExistingId_returns404() throws Exception {
        when(clubService.findById(99L))
                .thenThrow(new ClubNotFoundException("Club not found with id: 99"));

        mockMvc.perform(get("/api/clubs/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void findAll_withoutToken_returns403() throws Exception {
        mockMvc.perform(get("/api/clubs"))
                .andExpect(status().isForbidden());
    }
}