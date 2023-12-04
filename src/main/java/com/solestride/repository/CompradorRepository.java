package com.solestride.repository;
import com.solestride.model.pessoa.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Long>{
}
