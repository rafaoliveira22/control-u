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
                "SELECT 'ACESSO' AS tipo, " +
                "DATE_FORMAT(acesso_entrada, '%d/%m/%Y') AS dataFormatada, " +
                "TIME(acesso_entrada) AS horario, " +
                "aluno_id AS referencia, " +
                "dispositivo_id AS descricao " +
                "FROM acesso WHERE acesso_entrada IS NOT NULL" +
                ") " +
                "UNION ALL " +
                "(" +
                "SELECT 'AULA' AS tipo, " +
                "DATE_FORMAT(aula_abertura, '%d/%m/%Y') AS dataFormatada, " +
                "TIME(aula_abertura) AS horario, " +
                "CONCAT(aula_id, \"_\", sala_id) AS referencia, " +
                "grade_id AS descricao " +
                "FROM aula WHERE aula_abertura IS NOT NULL" +
                ") " +
                "UNION ALL " +
                "(" +
                "SELECT 'PRESENCA' AS tipo, " +
                "DATE_FORMAT(presenca_entrada, '%d/%m/%Y') AS dataFormatada, " +
                "TIME(presenca_entrada) AS horario, " +
                "aluno_id AS referencia, " +
                "aula_id AS descricao " +
                "FROM presenca WHERE presenca_entrada IS NOT NULL" +
                ") " +
                "ORDER BY data_hora DESC LIMIT 10";

        Query query = entityManager.createNativeQuery(sql, "RegistrosRecentesMapping");

        return query.getResultList();
    }
}
