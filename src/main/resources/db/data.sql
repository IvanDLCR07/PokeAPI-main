USE pokemondb;

-- ============================================================
-- 1. Insertar Entrenadores (Trainers)
-- ============================================================
INSERT INTO trainers (name, hometown) VALUES
('Ash Ketchum', 'Pueblo Paleta'),
('Misty', 'Ciudad Celeste'),
('Brock', 'Ciudad Plateada'),
('Gary Oak', 'Pueblo Paleta');

-- ============================================================
-- 2. Insertar Pokémon
--    (Algunos tienen entrenador y otros son salvajes con trainer_id = NULL)
-- ============================================================
INSERT INTO pokemon (name, imagen_url, `type`, trainer_id) VALUES
('Pikachu', 'https://pokemondb.net', 'Eléctrico', 1),
('Charizard', 'https://pokemondb.net', 'Fuego', 1),
('Starmie', 'https://pokemondb.net', 'Agua', 2),
('Psyduck', 'https://pokemondb.net', 'Agua', 2),
('Onix', 'https://pokemondb.net', 'Roca', 3),
('Blastoise', 'https://pokemondb.net', 'Agua', 4),
('Mewtwo', 'https://pokemondb.net', 'Psíquico', NULL), -- Salvaje
('Snorlax', 'https://pokemondb.net', 'Normal', NULL);   -- Salvaje

-- ============================================================
-- 3. Insertar Estadísticas de los Pokémon (Relación 1:1)
--    (El pokemon_id se corresponde con el orden de inserción anterior)
-- ============================================================
INSERT INTO pokemon_stats (hp, attack, defense, speed, height, weight, pokemon_id) VALUES
(35, 55, 40, 90, 0.4, 6.0, 1),    -- Pikachu (ID: 1)
(78, 84, 78, 100, 1.7, 90.5, 2),  -- Charizard (ID: 2)
(60, 75, 85, 115, 1.1, 80.0, 3),  -- Starmie (ID: 3)
(50, 52, 48, 55, 0.8, 19.6, 4),   -- Psyduck (ID: 4)
(35, 45, 160, 70, 8.8, 210.0, 5), -- Onix (ID: 5)
(79, 83, 100, 78, 1.6, 85.5, 6),  -- Blastoise (ID: 6)
(106, 110, 90, 130, 2.0, 122.0, 7),-- Mewtwo (ID: 7)
(160, 110, 65, 30, 2.1, 460.0, 8); -- Snorlax (ID: 8)

-- ============================================================
-- 4. Insertar Habilidades (Abilities)
-- ============================================================
INSERT INTO abilities (name, description) VALUES
('Electricidad Estática', 'Puede paralizar al mínimo contacto físico.'),
('Mar Llamas', 'Potencia los ataques de tipo fuego cuando le queda poca salud.'),
('Cura Natural', 'Cura los problemas de estado al salir del combate.'),
('Aclimatación', 'Elimina los efectos del clima en el combate.'),
('Cabeza Roca', 'Evita el daño de retroceso al usar ciertos movimientos.'),
('Torrente', 'Potencia los ataques de tipo agua cuando le queda poca salud.'),
('Presión', 'Aumenta el consumo de PP de los movimientos del rival.'),
('Inmunidad', 'Evita que el Pokémon sea envenenado.');

-- ============================================================
-- 5. Insertar Relación Pokémon <-> Habilidades (Relación N:M)
-- ============================================================
INSERT INTO pokemon_abilities (pokemon_id, ability_id) VALUES
(1, 1), -- Pikachu tiene Electricidad Estática
(2, 2), -- Charizard tiene Mar Llamas
(3, 3), -- Starmie tiene Cura Natural
(4, 4), -- Psyduck tiene Aclimatación
(5, 5), -- Onix tiene Cabeza Roca
(6, 6), -- Blastoise tiene Torrente
(7, 7), -- Mewtwo tiene Presión
(8, 8); -- Snorlax tiene Inmunidad
