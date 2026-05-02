package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Event;
import com.svalero.swimhub.exception.EventNotFoundException;
import com.svalero.swimhub.service.EventService;
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
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Test
    @WithMockUser
    void findAll_returnsOk() throws Exception {
        Event e1 = new Event();
        e1.setId(1L);
        e1.setName("Campeonato Aragonés 2025");

        when(eventService.findAll(isNull(), isNull(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(e1)));

        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Campeonato Aragonés 2025"));
    }

    @Test
    @WithMockUser
    void findById_existingId_returnsOk() throws Exception {
        Event e = new Event();
        e.setId(1L);
        e.setName("Campeonato Aragonés 2025");

        when(eventService.findById(1L)).thenReturn(e);

        mockMvc.perform(get("/api/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Campeonato Aragonés 2025"));
    }

    @Test
    @WithMockUser
    void findById_nonExistingId_returns404() throws Exception {
        when(eventService.findById(99L))
                .thenThrow(new EventNotFoundException("Event not found with id: 99"));

        mockMvc.perform(get("/api/events/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404));
    }

    @Test
    void findAll_withoutToken_returns403() throws Exception {
        mockMvc.perform(get("/api/events"))
                .andExpect(status().isForbidden());
    }
}