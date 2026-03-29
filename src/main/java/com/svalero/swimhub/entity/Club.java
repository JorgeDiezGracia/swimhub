package com.svalero.swimhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private League league;

    @OneToMany(mappedBy = "club")
    private List<Swimmer> swimmers;
}
