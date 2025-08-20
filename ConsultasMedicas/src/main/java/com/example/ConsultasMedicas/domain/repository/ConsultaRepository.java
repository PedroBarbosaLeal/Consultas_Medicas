package com.example.ConsultasMedicas.domain.repository;

import com.example.ConsultasMedicas.domain.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
   List<Consulta> findByMedicoId(Long id);
   List<Consulta> findByPacienteId(Long pacienteId);

   List<Consulta> findAllByOrderByDataAsc();
   List<Consulta> findAllByOrderByDataDesc();
   boolean existsByMedicoIdAndData(Long medicoId, LocalDateTime data);
}
