package com.example.pokeApi.service;

import java.util.List;

import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.persistance.model.Trainer;

public interface TrainerService {

    List<Trainer> getAllTrainers();
    Trainer getTrainerById(Long id);
    Trainer createTrainer(Trainer trainer);
    Trainer addPokemonToTrainer(Long trainerId, Long pokemonId);
    List<Pokemon> getPokemonsByTrainerId(Long trainerId);
    void deleteTrainer(Long id);

    //Devuelve el entrenador con el número de
    //Pokémon que tiene y la lista de tipos distintos
    //de esos Pokémon. 404 si no existe.

    Trainer getTrainerWithPokemonCountAndTypes(Long trainerId);

}
    