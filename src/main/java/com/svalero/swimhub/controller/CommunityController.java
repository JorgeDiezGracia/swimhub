package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Community;
import com.svalero.swimhub.exception.CommunityNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    private final Logger logger = LoggerFactory.getLogger(CommunityController.class);

    @GetMapping
    public ResponseEntity<List<Community>> findAll() {
        logger.info("BEGIN findAll communities");
        List<Community> communities = communityService.findAll();
        logger.info("END findAll communities");
        return ResponseEntity.ok(communities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> findById(@PathVariable Long id)
            throws CommunityNotFoundException {
        logger.info("BEGIN findById community {}", id);
        Community community = communityService.findById(id);
        logger.info("END findById community {}", id);
        return ResponseEntity.ok(community);
    }

    @ExceptionHandler(CommunityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommunityNotFoundException(
            CommunityNotFoundException exception) {
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
