package com.svalero.swimhub.service;

import com.svalero.swimhub.entity.Race;
import com.svalero.swimhub.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RaceService {

    private final RaceRepository raceRepository;

    public List<Race> findAll() {
        return raceRepository.findAll();
    }

    public Race findById(Long id) {
        return raceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Race not found with id: " + id));
    }
}
