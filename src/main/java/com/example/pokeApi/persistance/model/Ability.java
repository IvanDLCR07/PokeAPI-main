package com.example.pokeApi.persistance.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "abilities")
@NoArgsConstructor 
@Getter 
@Setter 
public class Ability {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column (name = "name")
    private String name;
    @Column (name = "description")
    private String description;

    @ManyToMany (mappedBy = "abilities")
    private Set<Pokemon> pokemons;
}
