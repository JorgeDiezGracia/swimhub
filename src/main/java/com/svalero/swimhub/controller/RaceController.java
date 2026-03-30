package com.svalero.swimhub.controller;

import com.svalero.swimhub.entity.Race;
import com.svalero.swimhub.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/races")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;

    @GetMapping
    public ResponseEntity<List<Race>> findAll() {
        return ResponseEntity.ok(raceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> findById(@PathVariable Long id) {
        return ResponseEntity.ok(raceService.findById(id));
    }
}
