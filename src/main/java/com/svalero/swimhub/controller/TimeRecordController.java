package com.svalero.swimhub.controller;

import com.svalero.swimhub.entity.TimeRecord;
import com.svalero.swimhub.service.TimeRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/time-records")
@RequiredArgsConstructor
public class TimeRecordController {

    private final TimeRecordService timeRecordService;

    @GetMapping
    public ResponseEntity<List<TimeRecord>> findAll() {
        return ResponseEntity.ok(timeRecordService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeRecord> findById(@PathVariable Long id) {
        return ResponseEntity.ok(timeRecordService.findById(id));
    }
}
