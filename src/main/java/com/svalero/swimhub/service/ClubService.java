package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Club;
import com.svalero.swimhub.exception.ClubNotFoundException;
import com.svalero.swimhub.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    public Club findById(Long id) throws ClubNotFoundException {
        return clubRepository.findById(id)
                .orElseThrow(() -> new ClubNotFoundException(
                        "Club not found with id: " + id));
    }
}
