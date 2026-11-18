package com.example.pokeApi.controller;

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

import com.example.pokeApi.controller.converter.PokemonConverter;
import com.example.pokeApi.controller.converter.TrainerConverter;
import com.example.pokeApi.controller.dto.PokemonDto;
import com.example.pokeApi.controller.dto.TrainerDto;
import com.example.pokeApi.service.TrainerService;

import lombok.AllArgsConstructor;

@RestController 
@CrossOrigin 
@AllArgsConstructor 
@RequestMapping("/api/v2/trainers")
public class TrainerController {
    
    TrainerService trainerService;
    TrainerConverter trainerConverter;
    PokemonConverter pokemonConverter;

    @GetMapping 
    public ResponseEntity<List<TrainerDto>> getAllTrainers() {
    
        return ResponseEntity.status(HttpStatus.OK).body(trainerService.getAllTrainers().stream().map(trainerConverter::entityToDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDto> getTrainerById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(trainerConverter.entityToDto(trainerService.getTrainerById(id)));
    }

    @PostMapping 
    public ResponseEntity<TrainerDto> createTrainer(@RequestBody TrainerDto trainerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainerConverter.entityToDto(trainerService.createTrainer(trainerConverter.dtoToEntity(trainerDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{trainerId}/pokemons/{pokemonId}")
    public ResponseEntity<TrainerDto> addPokemonToTrainer(@PathVariable Long trainerId, @PathVariable Long pokemonId) {
        return ResponseEntity.status(HttpStatus.OK).body(trainerConverter.entityToDto(trainerService.addPokemonToTrainer(trainerId, pokemonId)));
    }

    @GetMapping("/{trainerId}/pokemons")
    public ResponseEntity<List<PokemonDto>> getPokemonsByTrainerId(@PathVariable Long trainerId) {
        return ResponseEntity.status(HttpStatus.OK).body(trainerService.getPokemonsByTrainerId(trainerId)
                .stream().map(pokemonConverter::entityToDto).toList());
    }

    @GetMapping("/{trainerId}/summary")
    public ResponseEntity<TrainerDto> getTrainerWithPokemonCountAndTypes(@PathVariable Long trainerId) {
        return ResponseEntity.status(HttpStatus.OK).body(trainerConverter.entityToDto(trainerService.getTrainerWithPokemonCountAndTypes(trainerId)));
    }

}
