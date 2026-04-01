package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Community;
import com.svalero.swimhub.exception.CommunityNotFoundException;
import com.svalero.swimhub.repository.CommunityRepository;
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
public class CommunityServiceTest {

    @Mock
    private CommunityRepository communityRepository;

    @InjectMocks
    private CommunityService communityService;

    @Test
    void findAll_returnsList() {
        Community c1 = new Community();
        c1.setId(1L);
        c1.setName("Aragón");
        c1.setCode("AR");

        Community c2 = new Community();
        c2.setId(2L);
        c2.setName("Cataluña");
        c2.setCode("CA");

        when(communityRepository.findAll()).thenReturn(List.of(c1, c2));

        List<Community> result = communityService.findAll();

        assertEquals(2, result.size());
        assertEquals("Aragón", result.get(0).getName());
        verify(communityRepository, times(1)).findAll();
    }

    @Test
    void findById_existingId_returnsCommunity() throws CommunityNotFoundException {
        Community c = new Community();
        c.setId(1L);
        c.setName("Aragón");
        c.setCode("AR");

        when(communityRepository.findById(1L)).thenReturn(Optional.of(c));

        Community result = communityService.findById(1L);

        assertEquals("Aragón", result.getName());
        verify(communityRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(communityRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CommunityNotFoundException.class,
                () -> communityService.findById(99L));
    }
}
