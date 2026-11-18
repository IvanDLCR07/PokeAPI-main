package com.example.pokeApi.service.impl;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.persistance.repository.PokemonRepository;
import com.example.pokeApi.service.PokemonService;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Override
    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }

    @Override
    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    @Override
    public Pokemon getPokemonById(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon not found: " + id));
    }

    @Override
    public Pokemon createPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Override
    public Pokemon updatePokemon(Long id, Pokemon pokemon) {
        Pokemon existingPokemon = getPokemonById(id);
        existingPokemon.setName(pokemon.getName());
        existingPokemon.setImagenUrl(pokemon.getImagenUrl());
        existingPokemon.setType(pokemon.getType());
        existingPokemon.setTrainer(pokemon.getTrainer());
        return pokemonRepository.save(existingPokemon);
    }

    
}
