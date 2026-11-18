package com.example.pokeApi.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pokeApi.persistance.model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    
}
