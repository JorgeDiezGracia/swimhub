package com.svalero.swimhub.controller;

import com.svalero.swimhub.entity.Swimmer;
import com.svalero.swimhub.service.SwimmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/swimmers")
@RequiredArgsConstructor
public class SwimmerController {

    private final SwimmerService swimmerService;

    @GetMapping
    public ResponseEntity<List<Swimmer>> findAll() {
        return ResponseEntity.ok(swimmerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Swimmer> findById(@PathVariable Long id) {
        return ResponseEntity.ok(swimmerService.findById(id));
    }
}
