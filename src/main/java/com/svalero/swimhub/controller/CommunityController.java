package com.svalero.swimhub.controller;

import com.svalero.swimhub.entity.Community;
import com.svalero.swimhub.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    public ResponseEntity<List<Community>> findAll() {
        return ResponseEntity.ok(communityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> findById(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.findById(id));
    }
}
