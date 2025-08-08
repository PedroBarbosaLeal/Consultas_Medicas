package com.example.ConsultasMedicas.domain.repository;

import com.example.ConsultasMedicas.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
    boolean existsMedicoByCrm(String crm);
}
