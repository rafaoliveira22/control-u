ALTER TABLE aula
MODIFY COLUMN aula_abertura TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
MODIFY COLUMN aula_fechamento TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP;
