package com.svalero.swimhub.controller;

import com.svalero.swimhub.domain.Club;
import com.svalero.swimhub.domain.Swimmer;
import com.svalero.swimhub.exception.ClubNotFoundException;
import com.svalero.swimhub.exception.ErrorResponse;
import com.svalero.swimhub.service.ClubService;
import com.svalero.swimhub.service.SwimmerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final SwimmerService swimmerService;
    private final Logger logger = LoggerFactory.getLogger(ClubController.class);

    @GetMapping
    public ResponseEntity<List<Club>> findAll() {
        logger.info("BEGIN findAll clubs");
        List<Club> clubs = clubService.findAll();
        logger.info("END findAll clubs");
        return ResponseEntity.ok(clubs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> findById(@PathVariable Long id)
            throws ClubNotFoundException {
        logger.info("BEGIN findById club {}", id);
        Club club = clubService.findById(id);
        logger.info("END findById club {}", id);
        return ResponseEntity.ok(club);
    }

    @GetMapping("/{id}/swimmers")
    public ResponseEntity<List<Swimmer>> findSwimmers(@PathVariable Long id)
            throws ClubNotFoundException {
        logger.info("BEGIN findSwimmers by club {}", id);
        List<Swimmer> swimmers = swimmerService.findByClubId(id);
        logger.info("END findSwimmers by club {}", id);
        return ResponseEntity.ok(swimmers);
    }

    @ExceptionHandler(ClubNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClubNotFoundException(
            ClubNotFoundException exception) {
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
