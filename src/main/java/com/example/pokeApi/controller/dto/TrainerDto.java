package com.example.pokeApi.controller.dto;

import lombok.Data;

@Data 
public class TrainerDto {
    private Long id;
    private String name;
    private String hometown;
    private int pokemonCount;
    private int typesCount;
}
