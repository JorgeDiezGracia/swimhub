package com.svalero.swimhub.service;

import com.svalero.swimhub.entity.League;
import com.svalero.swimhub.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueRepository leagueRepository;

    public List<League> findAll() {
        return leagueRepository.findAll();
    }

    public League findById(Long id) {
        return leagueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("League not found with id: " + id));
    }
}
