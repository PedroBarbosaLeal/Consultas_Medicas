package com.example.ConsultasMedicas.service;

import com.example.ConsultasMedicas.domain.Paciente;
import com.example.ConsultasMedicas.dto.AtualizarPaciente;
import com.example.ConsultasMedicas.infra.exceptions.EsseIdNaoExiste;
import com.example.ConsultasMedicas.domain.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public Paciente CadastrarPaciente(Paciente dados) {
        return repository.save(dados);
    }

    public List<Paciente> listarPaciente() {
        return repository.findAll();
    }

    public List<Paciente> listarPacientePorDataAsc() {
        return repository.findAllByOrderByDataDeNascimentoAsc();
    }

    public List<Paciente> listarPacientePorDataDesc() {
        return repository.findAllByOrderByDataDeNascimentoDesc();
    }

    public Paciente listarPacienteId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EsseIdNaoExiste("ID não encontrado"));
    }


    @Transactional
    public void deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EsseIdNaoExiste("ID não encontrado");
        }
    }

    @Transactional
    public Paciente atualizar(Long id, AtualizarPaciente dados) {
        Paciente paciente = repository.findById(id).orElseThrow(() -> new EsseIdNaoExiste("ID não encontrado"));
        paciente.atualizarPaciente(dados);
        return paciente;
    }
}
