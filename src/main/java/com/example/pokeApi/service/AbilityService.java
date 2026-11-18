package com.example.pokeApi.service;

import java.util.List;

import com.example.pokeApi.controller.dto.AbilityDto;
import com.example.pokeApi.persistance.model.Ability;
import com.example.pokeApi.persistance.model.Pokemon;

public interface AbilityService {
    List<Ability> getAllAbilities();
    Ability createAbility(AbilityDto abilityDto);
    Ability addAbilityToPokemon(Long abilityId, Long pokemonId);
    Ability removeAbilityFromPokemon(Long abilityId, Long pokemonId);
    List<Ability> getAbilitiesByPokemonId(Long pokemonId);
    List<Ability> getAbilitiesByAbilityId(Long abilityId);
    List<Pokemon> getPokemonsByAbilityId(Long abilityId);
}
