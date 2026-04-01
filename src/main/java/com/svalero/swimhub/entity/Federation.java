package com.svalero.swimhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "federations")
public class Federation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 100)
    private String website;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @JsonIgnore
    @OneToMany(mappedBy = "federation")
    private List<League> leagues;
}
