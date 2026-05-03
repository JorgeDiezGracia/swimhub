package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Race;
import com.svalero.swimhub.exception.RaceNotFoundException;
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

    public Race findById(Long id) throws RaceNotFoundException {
        return raceRepository.findById(id)
                .orElseThrow(() -> new RaceNotFoundException(
                        "Race not found with id: " + id));
    }
}
