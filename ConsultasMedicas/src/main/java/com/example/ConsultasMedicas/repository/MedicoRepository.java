package com.example.ConsultasMedicas.repository;

import com.example.ConsultasMedicas.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
}
