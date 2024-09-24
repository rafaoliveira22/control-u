package com.controlu.backend.entity;

import com.controlu.backend.vo.RegistrosRecentesVO;

import jakarta.persistence.*;


@Entity
@SqlResultSetMapping(
        name = "RegistrosRecentesMapping",
        classes = @ConstructorResult(
                targetClass = RegistrosRecentesVO.class,
                columns = {
                        @ColumnResult(name = "tipo", type = String.class),
                        @ColumnResult(name = "dataFormatada", type = String.class),
                        @ColumnResult(name = "horario", type = String.class),
                        @ColumnResult(name = "referencia", type = String.class),
                        @ColumnResult(name = "descricao", type = String.class)
                }
        )
)

public class RegistrosRecentesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
