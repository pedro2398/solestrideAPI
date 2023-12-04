package com.solestride.model;
import com.solestride.model.pessoa.Fornecedor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PRODUTO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_COD_PRODUTO", columnNames = "COD_PRODUTO")
})
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @Column(name = "ID_PRODUTO")
    private Long id;

    @NotBlank
    @Column(name = "NM_NOME", nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "COD_PRODUTO", nullable = false)
    private String codProduto;

    @NotBlank
    @Column(name = "DESC_PRODUTO", nullable = false)
    private String descricao;

    @NotBlank
    @Column(name = "MODELO_PRODUTO", nullable = false)
    private String modelo;

    @NotBlank
    @Column(name = "FAB_PRODUTO", nullable = false)
    private String fabricante;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "ID_FORNECEDOR",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_PRODUTO_FORNECEDOR"),
            nullable = false

    )
    private Fornecedor fornecedores;
}
