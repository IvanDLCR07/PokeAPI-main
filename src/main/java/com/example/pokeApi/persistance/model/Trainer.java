package com.example.pokeApi.persistance.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "trainers")
@NoArgsConstructor 
@Getter 
@Setter 
public class Trainer {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column (name = "name")
    private String name;
    @Column (name = "hometown")
    private String hometown;
    @Transient
    private int pokemonCount;
    @Transient
    private int typesCount;
}