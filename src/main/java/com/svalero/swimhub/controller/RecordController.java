package com.svalero.swimhub.controller;

import com.svalero.swimhub.exception.RecordNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final Logger logger = LoggerFactory.getLogger(RecordController.class);

    @GetMapping
    public ResponseEntity<Page<com.svalero.swimhub.domain.Record>> findAll(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long federationId,
            @RequestParam(required = false) Long raceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        logger.info("BEGIN findAll records");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<com.svalero.swimhub.domain.Record> records = recordService.findAll(gender, categoryId, federationId, raceId, pageable);
        logger.info("END findAll records");
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.svalero.swimhub.domain.Record> findById(@PathVariable Long id)
            throws RecordNotFoundException {
        logger.info("BEGIN findById record {}", id);
        com.svalero.swimhub.domain.Record record = recordService.findById(id);
        logger.info("END findById record {}", id);
        return ResponseEntity.ok(record);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecordNotFoundException(
            RecordNotFoundException exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(
                ErrorResponse.notFound(exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(
                ErrorResponse.internalError(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
