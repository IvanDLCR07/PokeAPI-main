package com.example.pokeApi.controller.converter;

import org.springframework.stereotype.Component;

import com.example.pokeApi.controller.dto.PokemonStatsDto;
import com.example.pokeApi.persistance.model.PokemonStats;

import lombok.AllArgsConstructor;

@Component 
@AllArgsConstructor 
public class PokemonStatsConverter {
    
    public PokemonStatsDto entityToDto(PokemonStats pokemonStats) {
        PokemonStatsDto pokemonStatsDto = new PokemonStatsDto();
        pokemonStatsDto.setId(pokemonStats.getId());
        pokemonStatsDto.setHp(pokemonStats.getHp());
        pokemonStatsDto.setAttack(pokemonStats.getAttack());
        pokemonStatsDto.setDefense(pokemonStats.getDefense());
        pokemonStatsDto.setSpeed(pokemonStats.getSpeed());
        pokemonStatsDto.setHeight(pokemonStats.getHeight());
        pokemonStatsDto.setWeight(pokemonStats.getWeight());
        if (pokemonStats.getPokemon() != null) {
            pokemonStatsDto.setPokemonId(pokemonStats.getPokemon().getId());
        }
        return pokemonStatsDto;
    }

    public PokemonStats dtoToEntity(PokemonStatsDto pokemonStatsDto) {
        PokemonStats pokemonStats = new PokemonStats();
        pokemonStats.setId(pokemonStatsDto.getId());
        pokemonStats.setHp(pokemonStatsDto.getHp());
        pokemonStats.setAttack(pokemonStatsDto.getAttack());
        pokemonStats.setDefense(pokemonStatsDto.getDefense());
        pokemonStats.setSpeed(pokemonStatsDto.getSpeed());
        pokemonStats.setHeight(pokemonStatsDto.getHeight());
        pokemonStats.setWeight(pokemonStatsDto.getWeight());
        return pokemonStats;
    }

}
