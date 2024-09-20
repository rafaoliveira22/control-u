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

-- DASHBOARD
SELECT COUNT(*) FROM presenca WHERE DATE(presenca_entrada) = CURDATE() AND presenca_saida IS NULL;
SELECT COUNT(*) FROM acesso WHERE DATE(acesso_entrada) = CURDATE();
SELECT COUNT(*) FROM aluno;
SELECT COUNT(*) FROM grade;


SELECT TIME(acesso_entrada) FROM acesso; 

-- TESTE
-- TIPO -> ACESSO, AULA OU PRESENÇA
-- DATA_FORMATADA -> ENTRADA (INICIO DO REGISTRO) (QUANDO ACONTECEU)
-- HORARIO -> HORARIO DA DATA_FORMATADA
-- REFERENCIA -> O QUE INTERAGIU, 
-- ACESSSO -> ALUNO, 
-- AULA ->  AULA_ID + SALA_ID (AULA_ID_SALA_ID), 
-- PRESENCA -> AULA_ID


-- PEGAR ENTRADA E SAIDA, IDENTTIFICAR PELO TIPO

(SELECT 'ACESSO' AS tipo, DATE_FORMAT(acesso_entrada, '%d/%m/%Y') AS data_formatada, TIME(acesso_entrada) AS horario, aluno_id AS referencia, dispositivo_id AS descricao
 FROM acesso
 WHERE acesso_entrada IS NOT NULL)

UNION ALL

(SELECT 'AULA' AS tipo, DATE_FORMAT(aula_abertura, '%d/%m/%Y') AS data_formatada, TIME(aula_abertura) AS horario, CONCAT(aula_id, "_", sala_id) AS referencia, grade_id AS descricao
 FROM aula
 WHERE aula_abertura IS NOT NULL)

UNION ALL

(SELECT 'PRESENCA' AS tipo, DATE_FORMAT(presenca_entrada, '%d/%m/%Y') AS data_formatada, TIME(presenca_entrada) AS horario, aluno_id AS referencia, aula_id AS descricao
 FROM presenca
 WHERE presenca_entrada IS NOT NULL)

ORDER BY data_formatada DESC, horario DESC
LIMIT 10;


-- DEMONSTRAÇÃO AULA LAB. ENG. SOFW.
SELECT * FROM acesso WHERE DATE(acesso_entrada) = CURDATE();
SELECT * FROM aula WHERE DATE(aula_abertura) = CURDATE();






















