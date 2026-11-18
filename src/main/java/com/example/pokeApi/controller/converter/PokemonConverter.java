package com.example.pokeApi.controller.converter;

import com.example.pokeApi.controller.dto.PokemonDto;
import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.persistance.model.Trainer;
import org.springframework.stereotype.Component;

@Component
public class PokemonConverter {
    
    public PokemonDto entityToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setImagenUrl(pokemon.getImagenUrl());
        pokemonDto.setType(pokemon.getType());
        if (pokemon.getTrainer() != null) {
            pokemonDto.setTrainerId(pokemon.getTrainer().getId());
        }
        return pokemonDto;
    }

    public Pokemon dtoToEntity(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setImagenUrl(pokemonDto.getImagenUrl());
        pokemon.setType(pokemonDto.getType());
        if (pokemonDto.getTrainerId() != null) {
            Trainer trainer = new Trainer();
            trainer.setId(pokemonDto.getTrainerId());
            pokemon.setTrainer(trainer);
        }
        return pokemon;
    }

}
