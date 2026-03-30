package com.svalero.swimhub.service;

import com.svalero.swimhub.entity.Community;
import com.svalero.swimhub.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    public Community findById(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found with id: " + id));
    }
}
