package com.example.pokeApi.service;

import com.example.pokeApi.controller.dto.PokemonStatsDto;

public interface PokemonStatsService {
    PokemonStatsDto getPokemonStatsByPokemonId(Long pokemonId);
    PokemonStatsDto createPokemonStats(Long pokemonId, PokemonStatsDto pokemonStatsDto);
    PokemonStatsDto updatePokemonStats(Long pokemonId, PokemonStatsDto pokemonStatsDto);

}
