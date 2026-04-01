package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.League;
import com.svalero.swimhub.exception.LeagueNotFoundException;
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

    public League findById(Long id) throws LeagueNotFoundException {
        return leagueRepository.findById(id)
                .orElseThrow(() -> new LeagueNotFoundException(
                        "League not found with id: " + id));
    }
}
