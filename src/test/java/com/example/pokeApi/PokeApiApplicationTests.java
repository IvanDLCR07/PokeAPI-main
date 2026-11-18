package com.example.pokeApi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class PokeApiApplicationTests {

    private MockMvc mockMvc;

        @Autowired
        private WebApplicationContext webApplicationContext;

        @Autowired
        private ObjectMapper objectMapper;

        @BeforeEach
        void setUpMockMvc() {
                mockMvc = webAppContextSetup(webApplicationContext).build();
        }

    @Test
    void canCreateUpdateRetrieveAndDeletePokemon() throws Exception {
        MvcResult createResult = mockMvc.perform(post("/api/v2/pokemons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"pikachu\",\"imagenUrl\":\"https://pokeapi.co/api/v2/pokemon/25/\",\"type\":\"electric\"}"))
                .andExpect(status().isCreated())
                .andReturn();
        long pokemonId = objectMapper.readTree(createResult.getResponse().getContentAsString())
                .get("id").asLong();

        mockMvc.perform(put("/api/v2/pokemons/{id}", pokemonId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"raichu\",\"imagenUrl\":\"https://pokeapi.co/api/v2/pokemon/26/\",\"type\":\"electric\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("raichu"))
                .andExpect(jsonPath("$.imagenUrl").value("https://pokeapi.co/api/v2/pokemon/26/"));

        mockMvc.perform(get("/api/v2/pokemons/{id}", pokemonId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("raichu"))
                .andExpect(jsonPath("$.type").value("electric"));

        mockMvc.perform(delete("/api/v2/pokemons/{id}", pokemonId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v2/pokemons/{id}", pokemonId))
                .andExpect(status().isNotFound());
    }

    @Test
    void canListPokemonsByTrainerWithoutSerializingTrainerEntity() throws Exception {
        MvcResult trainerResult = mockMvc.perform(post("/api/v2/trainers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Ash\",\"hometown\":\"Pallet Town\"}"))
                .andExpect(status().isCreated())
                .andReturn();
        long trainerId = objectMapper.readTree(trainerResult.getResponse().getContentAsString())
                .get("id").asLong();

        MvcResult pokemonResult = mockMvc.perform(post("/api/v2/pokemons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Pikachu\",\"type\":\"electric\"}"))
                .andExpect(status().isCreated())
                .andReturn();
        long pokemonId = objectMapper.readTree(pokemonResult.getResponse().getContentAsString())
                .get("id").asLong();

        mockMvc.perform(put("/api/v2/trainers/{trainerId}/pokemons/{pokemonId}", trainerId, pokemonId))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v2/trainers/{trainerId}/pokemons", trainerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(pokemonId))
                .andExpect(jsonPath("$[0].name").value("Pikachu"))
                .andExpect(jsonPath("$[0].trainerId").value(trainerId));
    }

    @Test
    void trainerSummaryIncludesPokemonAndDistinctTypeCounts() throws Exception {
        MvcResult trainerResult = mockMvc.perform(post("/api/v2/trainers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Summary Trainer\",\"hometown\":\"Pallet Town\"}"))
                .andExpect(status().isCreated())
                .andReturn();
        long trainerId = objectMapper.readTree(trainerResult.getResponse().getContentAsString())
                .get("id").asLong();

        for (String type : new String[] { "fire, water", null }) {
            String body = type == null
                    ? "{\"name\":\"Type-less Pokemon\"}"
                    : "{\"name\":\"Typed Pokemon\",\"type\":\"" + type + "\"}";
            MvcResult pokemonResult = mockMvc.perform(post("/api/v2/pokemons")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                    .andExpect(status().isCreated())
                    .andReturn();
            long pokemonId = objectMapper.readTree(pokemonResult.getResponse().getContentAsString())
                    .get("id").asLong();

            mockMvc.perform(put("/api/v2/trainers/{trainerId}/pokemons/{pokemonId}", trainerId, pokemonId))
                    .andExpect(status().isOk());
        }

        mockMvc.perform(get("/api/v2/trainers/{trainerId}/summary", trainerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pokemonCount").value(2))
                .andExpect(jsonPath("$.typesCount").value(2));
    }
}
