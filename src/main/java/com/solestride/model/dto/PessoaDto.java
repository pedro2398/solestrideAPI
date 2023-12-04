package com.solestride.model.dto;

import com.solestride.model.pessoa.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {
    private Long id;
    private String nome;
    private String cnpj;
    private String email;
    private String modTributario;
    private String tipo;

    public PessoaDto(Pessoa pessoa) {
        id = pessoa.getId();
        nome = pessoa.getNome();
        cnpj = pessoa.getCnpj();
        email = pessoa.getEmail();
        modTributario = pessoa.getModTributario();
        tipo = pessoa.getModTributario();
    }
}
