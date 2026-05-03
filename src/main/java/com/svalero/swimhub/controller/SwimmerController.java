package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Record;
import com.svalero.swimhub.domain.Swimmer;
import com.svalero.swimhub.domain.TimeRecord;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.RecordService;
import com.svalero.swimhub.service.SwimmerService;
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
import java.util.List;

@RestController
@RequestMapping("/api/swimmers")
@RequiredArgsConstructor
public class SwimmerController {

    private final SwimmerService swimmerService;
    private final TimeRecordService timeRecordService;
    private final RecordService recordService;
    private final Logger logger = LoggerFactory.getLogger(SwimmerController.class);

    @GetMapping
    public ResponseEntity<Page<Swimmer>> findAll(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long clubId,
            @RequestParam(required = false) Long federationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        logger.info("BEGIN findAll swimmers");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Swimmer> swimmers = swimmerService.findAll(gender, categoryId, clubId, federationId, pageable);
        logger.info("END findAll swimmers");
        return ResponseEntity.ok(swimmers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Swimmer> findById(@PathVariable Long id)
            throws SwimmerNotFoundException {
        logger.info("BEGIN findById swimmer {}", id);
        Swimmer swimmer = swimmerService.findById(id);
        logger.info("END findById swimmer {}", id);
        return ResponseEntity.ok(swimmer);
    }

    @GetMapping("/{id}/time-records")
    public ResponseEntity<List<TimeRecord>> findTimeRecords(@PathVariable Long id)
            throws SwimmerNotFoundException {
        logger.info("BEGIN findTimeRecords by swimmer {}", id);
        List<TimeRecord> timeRecords = timeRecordService.findBySwimmerId(id);
        logger.info("END findTimeRecords by swimmer {}", id);
        return ResponseEntity.ok(timeRecords);
    }

    @GetMapping("/{id}/records")
    public ResponseEntity<List<com.svalero.swimhub.domain.Record>> findRecords(@PathVariable Long id)
            throws SwimmerNotFoundException {
        logger.info("BEGIN findRecords by swimmer {}", id);
        List<com.svalero.swimhub.domain.Record> records = recordService.findBySwimmerId(id);
        logger.info("END findRecords by swimmer {}", id);
        return ResponseEntity.ok(records);
    }

    @ExceptionHandler(SwimmerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSwimmerNotFoundException(
            SwimmerNotFoundException exception) {
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