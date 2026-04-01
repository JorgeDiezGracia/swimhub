package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Swimmer;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.SwimmerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/swimmers")
@RequiredArgsConstructor
public class SwimmerController {

    private final SwimmerService swimmerService;
    private final Logger logger = LoggerFactory.getLogger(SwimmerController.class);

    @GetMapping
    public ResponseEntity<List<Swimmer>> findAll(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long clubId,
            @RequestParam(required = false) Long federationId) {
        logger.info("BEGIN findAll swimmers");
        List<Swimmer> swimmers = swimmerService.findAll(gender, categoryId, clubId, federationId);
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
