package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.League;
import com.svalero.swimhub.exception.LeagueNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.LeagueService;
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
@RequestMapping("/api/leagues")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;
    private final Logger logger = LoggerFactory.getLogger(LeagueController.class);

    @GetMapping
    public ResponseEntity<Page<League>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        logger.info("BEGIN findAll leagues");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<League> leagues = leagueService.findAll(pageable);
        logger.info("END findAll leagues");
        return ResponseEntity.ok(leagues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<League> findById(@PathVariable Long id)
            throws LeagueNotFoundException {
        logger.info("BEGIN findById league {}", id);
        League league = leagueService.findById(id);
        logger.info("END findById league {}", id);
        return ResponseEntity.ok(league);
    }

    @ExceptionHandler(LeagueNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLeagueNotFoundException(
            LeagueNotFoundException exception) {
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