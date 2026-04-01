package com.svalero.swimhub.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @OneToMany(mappedBy = "club")
    private List<Swimmer> swimmers;
}
