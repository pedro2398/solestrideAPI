package com.solestride.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_REQUISICAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_COD_REQUISICAO", columnNames = "COD_REQUISICAO")
})
public class Requisicao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REQUISICAO")
    @Column(name = "ID_REQUISICAO")
    private Long id;

    @NotBlank
    @Column(name = "COD_REQUISICAO", nullable = false)
    private String codRequisicao;

    @NotBlank
    @Positive
    @Column(name = "QTD_REQUISICAO", nullable = false)
    private Integer quantidade;

    @NotBlank
    @Positive
    @Column(name = "TETO_REQUISICAO", nullable = false)
    private BigDecimal tetoAutomatico;

    @NotBlank
    @Column(name = "DT_REQUISICAO", nullable = false)
    private LocalDate data;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_REQ_PRODUTO",
            referencedColumnName = "ID_PRODUTO",
            foreignKey = @ForeignKey(name = "TB_REQUISICAO_FK_PRODUTO"),
            nullable = false
    )
    private Produto produto;
}