package com.controlu.backend.repository;

import com.controlu.backend.entity.DispositivoLeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DispositivoLeituraRepository extends JpaRepository<DispositivoLeitura, String> {
    @Query("SELECT d FROM DispositivoLeitura d WHERE d.dispositivoId LIKE :prefixo% ORDER BY d.dispositivoId DESC")
    DispositivoLeitura findTopByDispositivoIdStartingWithOrderByDispositivoIdDesc(@Param("prefixo") String prefixo);
}
