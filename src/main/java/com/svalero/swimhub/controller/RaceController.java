package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Race;
import com.svalero.swimhub.exception.RaceNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/races")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;
    private final Logger logger = LoggerFactory.getLogger(RaceController.class);

    @GetMapping
    public ResponseEntity<List<Race>> findAll() {
        logger.info("BEGIN findAll races");
        List<Race> races = raceService.findAll();
        logger.info("END findAll races");
        return ResponseEntity.ok(races);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> findById(@PathVariable Long id)
            throws RaceNotFoundException {
        logger.info("BEGIN findById race {}", id);
        Race race = raceService.findById(id);
        logger.info("END findById race {}", id);
        return ResponseEntity.ok(race);
    }

    @ExceptionHandler(RaceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRaceNotFoundException(
            RaceNotFoundException exception) {
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
