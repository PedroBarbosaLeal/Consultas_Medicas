package com.example.ConsultasMedicas.domain.repository;

import com.example.ConsultasMedicas.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
}
