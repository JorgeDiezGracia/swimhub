package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.TimeRecord;
import com.svalero.swimhub.exception.TimeRecordNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.TimeRecordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/time-records")
@RequiredArgsConstructor
public class TimeRecordController {

    private final TimeRecordService timeRecordService;
    private final Logger logger = LoggerFactory.getLogger(TimeRecordController.class);

    @GetMapping
    public ResponseEntity<List<TimeRecord>> findAll() {
        logger.info("BEGIN findAll timeRecords");
        List<TimeRecord> timeRecords = timeRecordService.findAll();
        logger.info("END findAll timeRecords");
        return ResponseEntity.ok(timeRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeRecord> findById(@PathVariable Long id)
            throws TimeRecordNotFoundException {
        logger.info("BEGIN findById timeRecord {}", id);
        TimeRecord timeRecord = timeRecordService.findById(id);
        logger.info("END findById timeRecord {}", id);
        return ResponseEntity.ok(timeRecord);
    }

    @ExceptionHandler(TimeRecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTimeRecordNotFoundException(
            TimeRecordNotFoundException exception) {
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
