package com.example.pokeApi.controller;

import org.springframework.stereotype.Component;

import com.example.pokeApi.controller.dto.AbilityDto;
import com.example.pokeApi.persistance.model.Ability;

@Component
public class AbilityConverter {

    public AbilityDto entityToDto(Ability ability) {
        AbilityDto abilityDto = new AbilityDto();
        abilityDto.setId(ability.getId());
        abilityDto.setName(ability.getName());
        abilityDto.setDescription(ability.getDescription());
        return abilityDto;
    }
}
