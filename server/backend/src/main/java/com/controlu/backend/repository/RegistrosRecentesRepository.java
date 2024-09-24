package com.controlu.backend.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.controlu.backend.vo.RegistrosRecentesVO;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RegistrosRecentesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RegistrosRecentesVO> findRegistrosRecentes() {
        String sql = "(" +
                "SELECT 'ACESSO_ENTRADA' AS tipo, " +
                "DATE_FORMAT(acesso_entrada, '%d/%m/%Y') AS dataFormatada, " +
                "TIME(acesso_entrada) AS horario, " +
                "aluno_id AS referencia, " +
                "dispositivo_id AS descricao " +
                "FROM acesso " +
                "WHERE acesso_entrada IS NOT NULL" +
                ") " +
                "UNION ALL " +
                "(" +
                "SELECT 'ACESSO_SAIDA' AS tipo, " +
                "DATE_FORMAT(acesso_saida, '%d/%m/%Y') AS dataFormatada, " +
                "TIME(acesso_saida) AS horario, " +
                "aluno_id AS referencia, " +
                "dispositivo_id AS descricao " +
                "FROM acesso " +
                "WHERE acesso_saida IS NOT NULL" +
                ") " +
                "UNION ALL " +
                "(" +
                "SELECT 'AULA_ABERTURA' AS tipo, " +
                "DATE_FORMAT(aula_abertura, '%d/%m/%Y') AS dataFormatada, " +
                "TIME(aula_abertura) AS horario, " +
                "CONCAT(aula_id, '_', sala_id) AS referencia, " +
                "grade_id AS descricao " +
                "FROM aula " +
                "WHERE aula_abertura IS NOT NULL" +
                ") " +
                "UNION ALL " +
                "(" +
                "SELECT 'AULA_FECHAMENTO' AS tipo, " +
                "DATE_FORMAT(aula_fechamento, '%d/%m/%Y') AS dataFormatada, " +
                "TIME(aula_fechamento) AS horario, " +
                "CONCAT(aula_id, '_', sala_id) AS referencia, " +
                "grade_id AS descricao " +
                "FROM aula " +
                "WHERE aula_fechamento IS NOT NULL" +
                ") " +
                "UNION ALL " +
                "(" +
                "SELECT 'PRESENCA_ENTRADA' AS tipo, " +
                "DATE_FORMAT(presenca_entrada, '%d/%m/%Y') AS dataFormatada, " +
                "TIME(presenca_entrada) AS horario, " +
                "aluno_id AS referencia, " +
                "aula_id AS descricao " +
                "FROM presenca " +
                "WHERE presenca_entrada IS NOT NULL" +
                ") " +
                "UNION ALL " +
                "(" +
                "SELECT 'PRESENCA_SAIDA' AS tipo, " +
                "DATE_FORMAT(presenca_saida, '%d/%m/%Y') AS dataFormatada, " +
                "TIME(presenca_saida) AS horario, " +
                "aluno_id AS referencia, " +
                "aula_id AS descricao " +
                "FROM presenca " +
                "WHERE presenca_saida IS NOT NULL" +
                ") " +
                "ORDER BY dataFormatada DESC, horario DESC " +
                "LIMIT 10;";


        Query query = entityManager.createNativeQuery(sql, "RegistrosRecentesMapping");
        return query.getResultList();
    }
}
