ALTER TABLE aula
MODIFY aula_id VARCHAR(18),
DROP PRIMARY KEY,
ADD PRIMARY KEY (aula_id);

ALTER TABLE presenca
MODIFY aula_id VARCHAR(18);
