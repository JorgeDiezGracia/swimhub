package com.svalero.swimhub.controller;

import com.svalero.swimhub.entity.Club;
import com.svalero.swimhub.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<List<Club>> findAll() {
        return ResponseEntity.ok(clubService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.findById(id));
    }
}
