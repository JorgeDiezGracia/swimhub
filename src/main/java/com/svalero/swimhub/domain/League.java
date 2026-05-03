package com.svalero.swimhub.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @OneToMany(mappedBy = "league")
    private List<Club> clubs;
}

