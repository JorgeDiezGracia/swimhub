package com.svalero.swimhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "`records`")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double time;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 1)
    private String gender;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "federation_id", nullable = false)
    private Federation federation;

    @ManyToOne
    @JoinColumn(name = "swimmer_id", nullable = false)
    private Swimmer swimmer;
}
