package com.example.ConsultasMedicas.domain.repository;

import com.example.ConsultasMedicas.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
   @Query("SELECT c FROM Consulta c WHERE c.medico.id_medico =:id")
   List<Consulta> findByMedicoId_medico(Long id);
   List<Consulta> findByPacienteId(Long pacienteId);
}
