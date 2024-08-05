ALTER TABLE aluno
ADD aluno_ano_ingressao INT NOT NULL,
ADD curso_id VARCHAR(20) NOT NULL,
ADD aluno_ra VARCHAR(20) NOT NULL;

ALTER TABLE aluno
ADD PRIMARY KEY (aluno_nome, curso_id, aluno_ano_ingressao);
