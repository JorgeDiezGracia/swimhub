package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Swimmer;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.repository.SwimmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SwimmerService {

    private final SwimmerRepository swimmerRepository;

    public List<Swimmer> findAll() {
        return swimmerRepository.findAll();
    }

    public Swimmer findById(Long id) throws SwimmerNotFoundException {
        return swimmerRepository.findById(id)
                .orElseThrow(() -> new SwimmerNotFoundException(
                        "Swimmer not found with id: " + id));
    }
}