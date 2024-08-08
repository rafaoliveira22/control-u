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
DELETE FROM aluno WHERE curso_id = 0;

-- CURSO
DESC curso;
SELECT * FROM curso;

-- PROFESSOR
DESC professor;
SELECT * FROM professor;
