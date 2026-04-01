package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Record;
import com.svalero.swimhub.exception.RecordNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final Logger logger = LoggerFactory.getLogger(RecordController.class);

    @GetMapping
    public ResponseEntity<List<Record>> findAll() {
        logger.info("BEGIN findAll records");
        List<Record> records = recordService.findAll();
        logger.info("END findAll records");
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> findById(@PathVariable Long id)
            throws RecordNotFoundException {
        logger.info("BEGIN findById record {}", id);
        Record record = recordService.findById(id);
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
