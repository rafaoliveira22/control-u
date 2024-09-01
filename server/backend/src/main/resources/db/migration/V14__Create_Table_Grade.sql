CREATE TABLE grade (
    grade_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    curso_id INTEGER NOT NULL,
    disciplina_id VARCHAR(10) NOT NULL,
    professor_id VARCHAR(20) NOT NULL,
    cartao_id VARCHAR(10) NOT NULL
);