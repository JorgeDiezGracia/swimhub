package com.svalero.swimhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String style;

    @Column(nullable = false)
    private Integer distance;

    @Column(name = "pool_type", nullable = false, length = 20)
    private String poolType;

    @OneToMany(mappedBy = "race")
    private List<TimeRecord> timeRecords;

    @OneToMany(mappedBy = "race")
    private List<Record> records;
}
