CREATE TABLE presenca (
    presenca_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    presenca_entrada DATETIME NOT NULL,
    presenca_saida DATETIME,
    aluno_id VARCHAR(13) NOT NULL,
    aula_id INT NOT NULL
);