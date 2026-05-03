package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Community;
import com.svalero.swimhub.exception.CommunityNotFoundException;
import com.svalero.swimhub.service.CommunityService;
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
public class CommunityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityService communityService;

    @Test
    @WithMockUser
    void findAll_returnsOk() throws Exception {
        Community c1 = new Community();
        c1.setId(1L);
        c1.setName("Aragón");
        c1.setCode("AR");

        when(communityService.findAll()).thenReturn(List.of(c1));

        mockMvc.perform(get("/api/communities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Aragón"))
                .andExpect(jsonPath("$[0].code").value("AR"));
    }

    @Test
    @WithMockUser
    void findById_existingId_returnsOk() throws Exception {
        Community c = new Community();
        c.setId(1L);
        c.setName("Aragón");
        c.setCode("AR");

        when(communityService.findById(1L)).thenReturn(c);

        mockMvc.perform(get("/api/communities/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Aragón"));
    }

    @Test
    @WithMockUser
    void findById_nonExistingId_returns404() throws Exception {
        when(communityService.findById(99L))
                .thenThrow(new CommunityNotFoundException("Community not found with id: 99"));

        mockMvc.perform(get("/api/communities/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void findAll_withoutToken_returns403() throws Exception {
        mockMvc.perform(get("/api/communities"))
                .andExpect(status().isForbidden());
    }
}