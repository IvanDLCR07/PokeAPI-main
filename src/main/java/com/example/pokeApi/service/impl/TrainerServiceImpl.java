package com.example.pokeApi.service.impl;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.persistance.model.Trainer;
import com.example.pokeApi.persistance.repository.PokemonRepository;
import com.example.pokeApi.persistance.repository.TrainerRepository;
import com.example.pokeApi.service.TrainerService;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class TrainerServiceImpl implements TrainerService{
    
    private final TrainerRepository trainerRepository;
    private final PokemonRepository pokemonRepository;

    @Override 
    public List<Trainer> getAllTrainers() {
       return trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found: " + id));
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer addPokemonToTrainer(Long trainerId, Long pokemonId) {
        Trainer trainer = getTrainerById(trainerId);
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon not found: " + pokemonId));
        pokemon.setTrainer(trainer);
        pokemonRepository.save(pokemon);
        return trainer;
    }

    @Override 
    public List<Pokemon> getPokemonsByTrainerId(Long trainerId) {
        getTrainerById(trainerId);
        return pokemonRepository.findByTrainer_Id(trainerId);
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }

    @Override
    public Trainer getTrainerWithPokemonCountAndTypes(Long trainerId) {
        Trainer trainer = getTrainerById(trainerId);
        List<Pokemon> pokemons = pokemonRepository.findByTrainer_Id(trainerId);
        int totalPokemonCount = pokemons.size();
        int totalTypesCount = pokemons.stream()
                .map(Pokemon::getTypes)
                .flatMap(List::stream)
                .distinct()
                .mapToInt(type -> 1)
                .sum();
        trainer.setPokemonCount(totalPokemonCount);
        trainer.setTypesCount(totalTypesCount);
        return trainer;
    }
    
}
