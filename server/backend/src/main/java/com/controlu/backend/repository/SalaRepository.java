package com.controlu.backend.repository;

import com.controlu.backend.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, String> {
    long countByDispositivoId(String dispositivoId);
    @Query("SELECT s FROM Sala s WHERE s.salaId LIKE :prefixo% ORDER BY s.salaId DESC")
    Sala findTopBySalaIdStartingWithOrderBySalaIdDesc(@Param("prefixo") String prefixo);
}
