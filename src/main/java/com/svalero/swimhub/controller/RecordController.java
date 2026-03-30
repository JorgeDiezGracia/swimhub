package com.svalero.swimhub.controller;

import com.svalero.swimhub.entity.Record;
import com.svalero.swimhub.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping
    public ResponseEntity<List<Record>> findAll() {
        return ResponseEntity.ok(recordService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> findById(@PathVariable Long id) {
        return ResponseEntity.ok(recordService.findById(id));
    }
}
