package com.example.pokeApi.controller.converter;

import org.springframework.stereotype.Component;

import com.example.pokeApi.controller.dto.TrainerDto;
import com.example.pokeApi.persistance.model.Trainer;

import lombok.AllArgsConstructor;

@Component 
@AllArgsConstructor 
public class TrainerConverter {
    
    public TrainerDto entityToDto(Trainer trainer) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setId(trainer.getId());
        trainerDto.setName(trainer.getName());
        trainerDto.setHometown(trainer.getHometown());
        trainerDto.setPokemonCount(trainer.getPokemonCount());
        trainerDto.setTypesCount(trainer.getTypesCount());
        return trainerDto;
    }

    public Trainer dtoToEntity(TrainerDto trainerDto) {
        Trainer trainer = new Trainer();
        trainer.setId(trainerDto.getId());
        trainer.setName(trainerDto.getName());
        trainer.setHometown(trainerDto.getHometown());
        return trainer;
    }

}
