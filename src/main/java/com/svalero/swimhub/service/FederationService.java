package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Federation;
import com.svalero.swimhub.exception.FederationNotFoundException;
import com.svalero.swimhub.repository.FederationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FederationService {

    private final FederationRepository federationRepository;

    public List<Federation> findAll() {
        return federationRepository.findAll();
    }

    public Federation findById(Long id) throws FederationNotFoundException {
        return federationRepository.findById(id)
                .orElseThrow(() -> new FederationNotFoundException(
                        "Federation not found with id: " + id));
    }
}
