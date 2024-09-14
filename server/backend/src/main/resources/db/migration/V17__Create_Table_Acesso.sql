CREATE TABLE acesso (
    acesso_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    acesso_entrada DATETIME NOT NULL,
    acesso_saida DATETIME,
    aluno_id VARCHAR(13) NOT NULL,
    dispositivo_id VARCHAR(10) NOT NULL
);