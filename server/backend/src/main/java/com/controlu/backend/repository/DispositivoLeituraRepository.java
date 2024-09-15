package com.controlu.backend.repository;

import com.controlu.backend.entity.DispositivoLeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DispositivoLeituraRepository extends JpaRepository<DispositivoLeitura, String> {
    List<DispositivoLeitura> findByDispositivoStatus(Integer dispositivoStatus);
    DispositivoLeitura findTopByDispositivoIdStartingWithOrderByDispositivoIdDesc(String prefixo);

}
