package com.example.ConsultasMedicas.service;

import com.example.ConsultasMedicas.domain.Consulta;
import com.example.ConsultasMedicas.domain.Medico;
import com.example.ConsultasMedicas.domain.Paciente;
import com.example.ConsultasMedicas.domain.repository.ConsultaRepository;
import com.example.ConsultasMedicas.domain.repository.MedicoRepository;
import com.example.ConsultasMedicas.domain.repository.PacienteRepository;
import com.example.ConsultasMedicas.infra.exceptions.EsseIdNaoExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;


    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public Consulta criarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Paciente listarConsultaPorIdPaciente(Long idPaciente) {
        return pacienteRepository.findById(idPaciente).orElseThrow(() -> new EsseIdNaoExiste("Esse ID de Paciente não existe"));
    }

    public Medico listarConsultaPorIdMedico(Long idMedico) {
        return medicoRepository.findById(idMedico).orElseThrow(() -> new EsseIdNaoExiste("Esse ID de Medico não existe"));
    }


}
