package com.svalero.swimhub.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "time_records")
public class TimeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double time;

    @Column(nullable = false)
    private LocalDate date;

    @Column(length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "swimmer_id", nullable = false)
    private Swimmer swimmer;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
