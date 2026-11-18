package com.example.pokeApi.service.impl;

import java.util.List;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.example.pokeApi.controller.dto.AbilityDto;
import com.example.pokeApi.persistance.model.Ability;
import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.persistance.repository.AbilityRepository;
import com.example.pokeApi.persistance.repository.PokemonRepository;
import com.example.pokeApi.service.AbilityService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
@Transactional
public class AbilityServiceImpl implements AbilityService {

    private final AbilityRepository abilityRepository;
    private final PokemonRepository pokemonRepository;

    @Override
    public List<Ability> getAllAbilities() {
        return abilityRepository.findAll();
    }

    @Override
    public Ability createAbility(AbilityDto abilityDto) {
        Ability ability = new Ability();
        ability.setName(abilityDto.getName());
        ability.setDescription(abilityDto.getDescription());
        return abilityRepository.save(ability);
    }

    @Override
    public Ability addAbilityToPokemon(Long abilityId, Long pokemonId) {
        Ability ability = abilityRepository.findById(abilityId)
                .orElseThrow(() -> new EntityNotFoundException("Ability not found with id: " + abilityId));
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon not found with id: " + pokemonId));
        if (pokemon.getAbilities() == null) {
            pokemon.setAbilities(new HashSet<>());
        }
        pokemon.getAbilities().add(ability);
        pokemonRepository.save(pokemon);
        return ability;
    }

    @Override 
    public Ability removeAbilityFromPokemon(Long abilityId, Long pokemonId) {
        Ability ability = abilityRepository.findById(abilityId)
                .orElseThrow(() -> new EntityNotFoundException("Ability not found with id: " + abilityId));
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon not found with id: " + pokemonId));
        if (pokemon.getAbilities() != null) {
            pokemon.getAbilities().removeIf(existingAbility -> abilityId.equals(existingAbility.getId()));
        }
        pokemonRepository.save(pokemon);
        return ability;
    }

    @Override
    public List<Ability> getAbilitiesByPokemonId(Long pokemonId) {
        if (!pokemonRepository.existsById(pokemonId)) {
            throw new EntityNotFoundException("Pokemon not found with id: " + pokemonId);
        }
        return abilityRepository.findByPokemons_Id(pokemonId);
    }

    @Override
    public List<Ability> getAbilitiesByAbilityId(Long abilityId) {
        return List.of(abilityRepository.findById(abilityId)
                .orElseThrow(() -> new EntityNotFoundException("Ability not found with id: " + abilityId)));
    }

    @Override
    public List<Pokemon> getPokemonsByAbilityId(Long abilityId) {
        if (!abilityRepository.existsById(abilityId)) {
            throw new EntityNotFoundException("Ability not found with id: " + abilityId);
        }
        return pokemonRepository.findByAbilities_Id(abilityId);
    }
}
