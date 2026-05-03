package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.TimeRecord;
import com.svalero.swimhub.exception.TimeRecordNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.TimeRecordService;
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
@RequestMapping("/api/time-records")
@RequiredArgsConstructor
public class TimeRecordController {

    private final TimeRecordService timeRecordService;
    private final Logger logger = LoggerFactory.getLogger(TimeRecordController.class);

    @GetMapping
    public ResponseEntity<Page<TimeRecord>> findAll(
            @RequestParam(required = false) Long swimmerId,
            @RequestParam(required = false) Long raceId,
            @RequestParam(required = false) Long eventId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        logger.info("BEGIN findAll timeRecords");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<TimeRecord> timeRecords = timeRecordService.findAll(swimmerId, raceId, eventId, pageable);
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
