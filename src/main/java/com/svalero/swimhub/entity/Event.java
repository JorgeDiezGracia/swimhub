package com.svalero.swimhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @Column(length = 150)
    private String location;

    @Column(name = "pool_type", length = 20)
    private String poolType;

    @ManyToOne
    @JoinColumn(name = "federation_id", nullable = false)
    private Federation federation;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<TimeRecord> timeRecords;
}
