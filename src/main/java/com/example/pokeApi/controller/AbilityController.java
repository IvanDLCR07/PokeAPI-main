package com.example.pokeApi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokeApi.controller.converter.PokemonConverter;
import com.example.pokeApi.controller.dto.AbilityDto;
import com.example.pokeApi.controller.dto.PokemonDto;
import com.example.pokeApi.persistance.model.Ability;
import com.example.pokeApi.service.AbilityService;

import java.util.List;

import lombok.AllArgsConstructor;

@RestController 
@CrossOrigin 
@AllArgsConstructor 
@RequestMapping ("/api/v2/abilities")
public class AbilityController {
    
    private final AbilityService abilityService;
    private final AbilityConverter abilityConverter;
    private final PokemonConverter pokemonConverter;

    @PostMapping("pokemons/{pokemonId}/abilities/{abilityId}")
    public Ability addAbilityToPokemon(@PathVariable Long abilityId, @PathVariable Long pokemonId) {
        return abilityService.addAbilityToPokemon(abilityId, pokemonId);
    }

    @GetMapping("pokemons/{pokemonId}/abilities")
    public List<AbilityDto> getAbilitiesByPokemonId(@PathVariable Long pokemonId) {
        return abilityService.getAbilitiesByPokemonId(pokemonId).stream()
                .map(abilityConverter::entityToDto)
                .toList();
    }

    @PostMapping("pokemons/{pokemonId}/abilities/{abilityId}/remove")
    public Ability removeAbilityFromPokemon(@PathVariable Long abilityId, @PathVariable Long pokemonId) {
        return abilityService.removeAbilityFromPokemon(abilityId, pokemonId);
    }

    @DeleteMapping("pokemons/{pokemonId}/abilities/{abilityId}")
    public void deleteAbilityFromPokemon(@PathVariable Long abilityId, @PathVariable Long pokemonId) {
        abilityService.removeAbilityFromPokemon(abilityId, pokemonId);
    }

    @GetMapping("abilities/{abilityId}/pokemons")
    public List<PokemonDto> getPokemonsByAbilityId(@PathVariable Long abilityId) {
        return abilityService.getPokemonsByAbilityId(abilityId).stream()
                .map(pokemonConverter::entityToDto)
                .toList();
    }
}
