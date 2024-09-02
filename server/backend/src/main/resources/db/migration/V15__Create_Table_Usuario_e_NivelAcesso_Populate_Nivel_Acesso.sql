CREATE TABLE usuario (
    usuario_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    usuario_nome VARCHAR(40) NOT NULL,
    usuario_senha TEXT NOT NULL,
    nivel_acesso_id INT NOT NULL
);

CREATE TABLE nivel_acesso (
    nivel_acesso_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nivel_acesso_nome VARCHAR(40) NOT NULL
);

INSERT INTO nivel_acesso (nivel_acesso_nome) VALUES ("ROLE_ADM"), ("ROLE_COMUM");