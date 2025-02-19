package com.felipe.aprendendo_spring.infrastructure.repository;

import com.felipe.aprendendo_spring.infrastructure.entity.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Enderecos, Long> {
}
