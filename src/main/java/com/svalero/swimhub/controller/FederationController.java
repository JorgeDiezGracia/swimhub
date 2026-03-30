package com.svalero.swimhub.controller;

import com.svalero.swimhub.entity.Federation;
import com.svalero.swimhub.service.FederationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/federations")
@RequiredArgsConstructor
public class FederationController {

    private final FederationService federationService;

    @GetMapping
    public ResponseEntity<List<Federation>> findAll() {
        return ResponseEntity.ok(federationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Federation> findById(@PathVariable Long id) {
        return ResponseEntity.ok(federationService.findById(id));
    }
}
