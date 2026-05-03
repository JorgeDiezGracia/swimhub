package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.dto.PerformanceTrendDto;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.service.PerformanceTrendService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/swimmers")
@RequiredArgsConstructor
public class PerformanceTrendController {

    private final PerformanceTrendService performanceTrendService;
    private final Logger logger = LoggerFactory.getLogger(PerformanceTrendController.class);

    @GetMapping("/{swimmerId}/performance")
    public ResponseEntity<PerformanceTrendDto> analyze(
            @PathVariable Long swimmerId,
            @RequestParam Long raceId) throws SwimmerNotFoundException {
        logger.info("BEGIN analyze performance swimmer {} race {}", swimmerId, raceId);
        PerformanceTrendDto dto = performanceTrendService.analyze(swimmerId, raceId);
        logger.info("END analyze performance swimmer {} race {}", swimmerId, raceId);
        return ResponseEntity.ok(dto);
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
