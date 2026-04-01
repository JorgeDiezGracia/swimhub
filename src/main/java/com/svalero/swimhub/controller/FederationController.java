package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Federation;
import com.svalero.swimhub.exception.FederationNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.FederationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/federations")
@RequiredArgsConstructor
public class FederationController {

    private final FederationService federationService;
    private final Logger logger = LoggerFactory.getLogger(FederationController.class);

    @GetMapping
    public ResponseEntity<List<Federation>> findAll() {
        logger.info("BEGIN findAll federations");
        List<Federation> federations = federationService.findAll();
        logger.info("END findAll federations");
        return ResponseEntity.ok(federations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Federation> findById(@PathVariable Long id)
            throws FederationNotFoundException {
        logger.info("BEGIN findById federation {}", id);
        Federation federation = federationService.findById(id);
        logger.info("END findById federation {}", id);
        return ResponseEntity.ok(federation);
    }

    @ExceptionHandler(FederationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFederationNotFoundException(
            FederationNotFoundException exception) {
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
