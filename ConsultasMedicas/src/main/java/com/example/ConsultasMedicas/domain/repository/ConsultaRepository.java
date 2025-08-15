package com.example.ConsultasMedicas.domain.repository;

import com.example.ConsultasMedicas.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
}
