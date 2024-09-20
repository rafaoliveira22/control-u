package com.controlu.backend.entity;

import com.controlu.backend.vo.RegistrosRecentesVO;

import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Entity;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;

@SqlResultSetMapping(
        name = "RegistrosRecentesMapping",
        classes = @ConstructorResult(
                targetClass = RegistrosRecentesVO.class,
                columns = {
                        @ColumnResult(name = "tipo"),
                        @ColumnResult(name = "dataFormatada"),
                        @ColumnResult(name = "horario"),
                        @ColumnResult(name = "referencia"),
                        @ColumnResult(name = "descricao")
                }
        )
)

public class RegistrosRecentesEntity {
}
