package com.controlu.backend.repository;

import com.controlu.backend.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, String> {
    Optional<Sala> findByDispositivoId(String dispositivoId);

    long countByDispositivoId(String dispositivoId);
    @Query("SELECT s FROM Sala s WHERE s.salaId LIKE :prefixo% ORDER BY s.salaId DESC")
    Sala findTopBySalaIdStartingWithOrderBySalaIdDesc(@Param("prefixo") String prefixo);
}
