package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Event;
import com.svalero.swimhub.domain.TimeRecord;
import com.svalero.swimhub.exception.EventNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.EventService;
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
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final TimeRecordService timeRecordService;
    private final Logger logger = LoggerFactory.getLogger(EventController.class);

    @GetMapping
    public ResponseEntity<Page<Event>> findAll(
            @RequestParam(required = false) Long federationId,
            @RequestParam(required = false) String poolType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        logger.info("BEGIN findAll events");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Event> events = eventService.findAll(federationId, poolType, pageable);
        logger.info("END findAll events");
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id)
            throws EventNotFoundException {
        logger.info("BEGIN findById event {}", id);
        Event event = eventService.findById(id);
        logger.info("END findById event {}", id);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/{id}/time-records")
    public ResponseEntity<List<TimeRecord>> findTimeRecords(@PathVariable Long id)
            throws EventNotFoundException {
        logger.info("BEGIN findTimeRecords by event {}", id);
        List<TimeRecord> timeRecords = timeRecordService.findByEventId(id);
        logger.info("END findTimeRecords by event {}", id);
        return ResponseEntity.ok(timeRecords);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEventNotFoundException(
            EventNotFoundException exception) {
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