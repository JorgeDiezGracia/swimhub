package com.svalero.swimhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "leagues")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String province;

    @ManyToOne
    @JoinColumn(name = "federation_id", nullable = false)
    private Federation federation;

    @OneToMany(mappedBy = "league")
    private List<Club> clubs;
}

