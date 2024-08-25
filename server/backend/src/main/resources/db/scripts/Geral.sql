USE db_controlu;
SHOW TABLES;

-- CONFIG
SET SQL_SAFE_UPDATES = 0;

-- FLYWAY
SELECT * FROM db_controlu.flyway_schema_history;
DELETE FROM flyway_schema_history WHERE version = '?';

-- ALUNO
DESC aluno;
SELECT * FROM aluno;

-- CURSO
DESC curso;
SELECT * FROM curso;

-- PROFESSOR
DESC professor;
SELECT * FROM professor;


-- DISPOSITIVO
DESC dispositivo_leitura;
SELECT * FROM dispositivo_leitura;

-- CARTAO
DESC cartao_leitura;
SELECT * FROM cartao_leitura;

-- SALA
SELECT * FROM sala;
DELETE FROM sala;
DELETE FROM dispositivo_leitura;
