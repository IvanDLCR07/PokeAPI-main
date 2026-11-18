package com.example.pokeApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokeApi.controller.converter.PokemonStatsConverter;
import com.example.pokeApi.controller.dto.PokemonStatsDto;
import com.example.pokeApi.service.PokemonService;
import com.example.pokeApi.service.PokemonStatsService;

import lombok.AllArgsConstructor;

@RestController 
@CrossOrigin 
@AllArgsConstructor 
@RequestMapping("/api/v2/pokemon_stats")
public class PokemonStatsController {

    PokemonStatsService pokemonStatsService;
    PokemonStatsConverter pokemonStatsConverter;
    PokemonService pokemonService;
    
    @PostMapping ("pokemons/{pokemonId}/stats")
    public ResponseEntity<PokemonStatsDto> createPokemonStats(@PathVariable Long pokemonId, @RequestBody PokemonStatsDto pokemonStatsDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonStatsService.createPokemonStats(pokemonId, pokemonStatsDto));
    }

    @GetMapping ("pokemons/{pokemonId}/stats")
    public ResponseEntity<PokemonStatsDto> getPokemonStats(@PathVariable Long pokemonId) {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonStatsService.getPokemonStatsByPokemonId(pokemonId));
    }

    @PutMapping ("pokemons/{pokemonId}/stats")
    public ResponseEntity<PokemonStatsDto> updatePokemonStats(@PathVariable Long pokemonId, @RequestBody PokemonStatsDto pokemonStatsDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonStatsService.updatePokemonStats(pokemonId, pokemonStatsDto));
    }

}
