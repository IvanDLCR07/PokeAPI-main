package com.example.pokeApi.persistance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "pokemon_stats")
@NoArgsConstructor 
@Getter 
@Setter 
public class PokemonStats {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column (name = "hp")
    private Integer hp;
    @Column (name = "attack")
    private Integer attack;
    @Column (name = "defense")
    private Integer defense;
    @Column (name = "speed")
    private Integer speed;
    @Column (name = "height")
    private Double height;
    @Column (name = "weight")
    private Double weight;

    @OneToOne
    @JoinColumn(name = "pokemon_id", nullable = false, unique = true)
    private Pokemon pokemon;
}
