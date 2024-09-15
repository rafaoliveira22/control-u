USE db_controlu;
SHOW TABLES;

-- CONFIG
SET SQL_SAFE_UPDATES = 0;

-- FLYWAY
SELECT * FROM db_controlu.flyway_schema_history;
-- DELETE FROM flyway_schema_history WHERE version = '';

-- ALUNO
DESC aluno;
SELECT * FROM aluno;
SHOW INDEX FROM aluno;


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


-- GRADE
DESC grade;
SELECT * FROM grade;

-- INNER JOIN
SELECT c.cartao_id FROM cartao_leitura c LEFT JOIN grade g ON c.cartao_id = g.cartao_id WHERE g.cartao_id IS NULL AND c.status = 2;
SELECT c FROM CartaoLeituran c LEFT JOIN Grade g ON c.cartaoId = g.cartaoId WHERE g.cartaoId IS NULL;

-- NIVEL ACESSO
SELECT * FROM nivel_acesso;

-- USUARIO
SELECT * FROM usuario;
-- DELETE FROM usuario;
-- ALTER TABLE usuario AUTO_INCREMENT = 1;

-- ACESSO
SELECT * FROM acesso;
SELECT * FROM acesso WHERE aluno_id = '2830482211024' AND DATE(acesso_entrada) = CURDATE();
SELECT * FROM acesso ORDER BY acesso_entrada DESC;
-- DELETE FROM acesso;
-- ALTER TABLE acesso AUTO_INCREMENT = 1;

-- FUSO HORARIO
SELECT @@global.time_zone, @@session.time_zone;
SET GLOBAL time_zone = '-03:00';
SET time_zone = '-03:00';

-- AULA
SELECT * FROM aula;
-- DELETE FROM aula;
-- ALTER TABLE aula AUTO_INCREMENT = 1;

-- PRESENCA
DESC presenca;
SELECT * FROM presenca;