package com.example.pokeApi.persistance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pokeApi.persistance.model.PokemonStats;

public interface PokemonStatsRepository extends JpaRepository<PokemonStats, Long> {

    Optional<PokemonStats> findByPokemonId(Long pokemonId);

}
