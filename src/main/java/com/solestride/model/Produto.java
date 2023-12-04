package com.solestride.model;
import com.solestride.model.pessoa.Fornecedor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.LinkedHashSet;
import java.util.Set;
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
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_PRODUTO_FORNECEDOR",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_PRODUTO",
                            referencedColumnName = "ID_PRODUTO",
                            foreignKey = @ForeignKey(name = "FK_PRODUTO_FORNECEDOR")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_PESSOA",
                            referencedColumnName = "ID_PESSOA",
                            foreignKey = @ForeignKey(name = "FK_FORNECEDOR_PRODUTO")
                    )
            }
    )
    private Set<Fornecedor> fornecedores = new LinkedHashSet<>();
}
