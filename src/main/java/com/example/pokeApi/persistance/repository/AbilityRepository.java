package com.example.pokeApi.persistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pokeApi.persistance.model.Ability;

public interface AbilityRepository extends JpaRepository<Ability, Long> {
    List<Ability> findByPokemons_Id(Long pokemonId);
}
