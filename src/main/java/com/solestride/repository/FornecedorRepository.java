package com.solestride.repository;

import com.solestride.model.pessoa.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
