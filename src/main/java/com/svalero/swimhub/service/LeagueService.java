package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.League;
import com.svalero.swimhub.exception.LeagueNotFoundException;
import com.svalero.swimhub.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueRepository leagueRepository;

    public Page<League> findAll(Pageable pageable) {
        return leagueRepository.findAll(pageable);
    }

    public League findById(Long id) throws LeagueNotFoundException {
        return leagueRepository.findById(id)
                .orElseThrow(() -> new LeagueNotFoundException(
                        "League not found with id: " + id));
    }
}
