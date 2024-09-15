CREATE TABLE presenca (
    aula_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    aula_abertura DATETIME NOT NULL,
    aula_fechamento DATETIME,
    grade_id INT NOT NULL,
    sala_id VARCHAR(10) NOT NULL
);