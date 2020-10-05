package com.tinnova.processo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tinnova.processo.domain.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
