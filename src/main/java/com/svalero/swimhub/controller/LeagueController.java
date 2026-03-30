package com.svalero.swimhub.controller;

import com.svalero.swimhub.entity.League;
import com.svalero.swimhub.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leagues")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;

    @GetMapping
    public ResponseEntity<List<League>> findAll() {
        return ResponseEntity.ok(leagueService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<League> findById(@PathVariable Long id) {
        return ResponseEntity.ok(leagueService.findById(id));
    }
}
