package com.example.pokeApi.controller;

import com.example.pokeApi.controller.converter.PokemonConverter;
import com.example.pokeApi.controller.dto.PokemonDto;
import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.service.PokemonService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/pokemons")
@CrossOrigin
@AllArgsConstructor 
public class PokemonController {

    PokemonService pokemonService;
    PokemonConverter pokemonConverter;

    @GetMapping
    public ResponseEntity<List<PokemonDto>> getAllPokemons() {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.getAllPokemons().stream().map(pokemonConverter::entityToDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonConverter.entityToDto(pokemonService.getPokemonById(id)));
    }

    @PostMapping
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonConverter.entityToDto(pokemonService.createPokemon(pokemonConverter.dtoToEntity(pokemonDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonDto> updatePokemon(@PathVariable Long id, @RequestBody PokemonDto pokemonDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonConverter.entityToDto(pokemonService.updatePokemon(id, pokemonConverter.dtoToEntity(pokemonDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
