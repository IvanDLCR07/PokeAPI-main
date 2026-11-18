package com.example.pokeApi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pokeApi.controller.converter.PokemonStatsConverter;
import com.example.pokeApi.controller.dto.PokemonStatsDto;
import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.persistance.model.PokemonStats;
import com.example.pokeApi.persistance.repository.PokemonRepository;
import com.example.pokeApi.persistance.repository.PokemonStatsRepository;
import com.example.pokeApi.service.PokemonStatsService;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class PokemonStatsServiceImpl implements PokemonStatsService {
    
    private final PokemonStatsRepository pokemonStatsRepository;
    private final PokemonRepository pokemonRepository;
    private final PokemonStatsConverter pokemonStatsConverter;

    @Override 
    public PokemonStatsDto getPokemonStatsByPokemonId(Long pokemonId) {
        return pokemonStatsRepository.findByPokemonId(pokemonId)
                .map(pokemonStatsConverter::entityToDto)
                .orElseThrow(() -> new RuntimeException("Pokemon stats not found for Pokemon ID: " + pokemonId));
    }

    @Override 
    public PokemonStatsDto createPokemonStats(Long pokemonId, PokemonStatsDto pokemonStatsDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon not found: " + pokemonId));
        if (pokemonStatsRepository.findByPokemonId(pokemonId).isPresent()) {
            throw new IllegalStateException("Pokemon stats already exist for Pokemon ID: " + pokemonId);
        }

        PokemonStats pokemonStats = pokemonStatsConverter.dtoToEntity(pokemonStatsDto);
        pokemonStats.setId(null);
        pokemonStats.setPokemon(pokemon);
        return pokemonStatsConverter.entityToDto(pokemonStatsRepository.save(pokemonStats));
    }

    @Override
    public PokemonStatsDto updatePokemonStats(Long pokemonId, PokemonStatsDto pokemonStatsDto) {
        PokemonStats pokemonStats = pokemonStatsRepository.findByPokemonId(pokemonId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pokemon stats not found for Pokemon ID: " + pokemonId));
        pokemonStats.setHp(pokemonStatsDto.getHp());
        pokemonStats.setAttack(pokemonStatsDto.getAttack());
        pokemonStats.setDefense(pokemonStatsDto.getDefense());
        pokemonStats.setSpeed(pokemonStatsDto.getSpeed());
        pokemonStats.setHeight(pokemonStatsDto.getHeight());
        pokemonStats.setWeight(pokemonStatsDto.getWeight());
        return pokemonStatsConverter.entityToDto(pokemonStatsRepository.save(pokemonStats));
    }
}
