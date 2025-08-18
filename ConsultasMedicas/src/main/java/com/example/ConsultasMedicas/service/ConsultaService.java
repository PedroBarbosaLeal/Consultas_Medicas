package com.example.ConsultasMedicas.service;

import com.example.ConsultasMedicas.domain.Consulta;
import com.example.ConsultasMedicas.domain.repository.ConsultaRepository;
import com.example.ConsultasMedicas.domain.repository.MedicoRepository;
import com.example.ConsultasMedicas.domain.repository.PacienteRepository;
import com.example.ConsultasMedicas.dto.AtualizarDataConsulta;
import com.example.ConsultasMedicas.infra.exceptions.EsseIdNaoExiste;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Consulta> listarConsultaPorIdPaciente(Long idPaciente) {

        return consultaRepository.findByPacienteId(idPaciente);
    }

    public List<Consulta> listarConsultaPorIdMedico(Long idMedico) {
        return consultaRepository.findByMedicoId_medico(idMedico);
    }

    public List<Consulta> listarConsultaPorDataAsc(){
        return consultaRepository.findAllByOrderByDataAsc();
    }

    public List<Consulta> listarConsultaPorDataDesc(){
        return consultaRepository.findAllByOrderByDataDesc();
    }

    @Transactional
    public Consulta atualizarADataDaConsulta(Long idConsulta, AtualizarDataConsulta dados) {
        Consulta consulta = consultaRepository.findById(idConsulta).orElseThrow(() -> new EsseIdNaoExiste("Essa consulta não existe"));
        consulta.AtualizarData(dados);
        return consulta;
    }

    @Transactional
    public void deletarConsulta(Long idConsulta) {
        if (consultaRepository.existsById(idConsulta)) {
            consultaRepository.deleteById(idConsulta);
        } else {
            throw new EsseIdNaoExiste("Esse ID de Consulta não existe");
        }
    }
}
