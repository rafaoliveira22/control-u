package com.controlu.backend.repository;

import com.controlu.backend.entity.model.DispositivoLeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispositivoLeituraRepository extends JpaRepository<DispositivoLeitura, String> {
    List<DispositivoLeitura> findByDispositivoStatus(Integer dispositivoStatus);
    DispositivoLeitura findTopByDispositivoIdStartingWithOrderByDispositivoIdDesc(String prefixo);

}
