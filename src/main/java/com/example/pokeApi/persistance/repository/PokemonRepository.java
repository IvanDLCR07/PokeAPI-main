package com.example.pokeApi.persistance.repository;

import java.util.List;

import com.example.pokeApi.persistance.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByTrainer_Id(Long trainerId);
    List<Pokemon> findByAbilities_Id(Long abilityId);
}
