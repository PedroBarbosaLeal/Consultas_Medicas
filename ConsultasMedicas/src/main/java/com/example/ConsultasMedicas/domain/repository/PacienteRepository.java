package com.example.ConsultasMedicas.domain.repository;

import com.example.ConsultasMedicas.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    List<Paciente> findAllByOrderByDataDeNascimentoAsc();
    List<Paciente> findAllByOrderByDataDeNascimentoDesc();
}
