package com.example.pokeApi.persistance.model;
        
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "imagen_url")
    private String imagenUrl;
    @Column(name = "type")
    private String type;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToOne(mappedBy = "pokemon")
    private PokemonStats pokemonStats;

    @ManyToMany
    @JoinTable(
        name = "pokemon_abilities",
        joinColumns = @JoinColumn(name = "pokemon_id"),
        inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private Set<Ability> abilities;

    public List<String> getTypes() {
        if (type == null || type.isBlank()) {
            return List.of();
        }
        return Arrays.stream(type.split(","))
                .map(String::trim)
                .filter(pokemonType -> !pokemonType.isEmpty())
                .distinct()
                .toList();
    }

}
