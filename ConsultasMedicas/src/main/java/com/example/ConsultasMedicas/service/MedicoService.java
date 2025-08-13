package com.example.ConsultasMedicas.service;

import com.example.ConsultasMedicas.domain.Medico;
import com.example.ConsultasMedicas.domain.repository.MedicoRepository;
import com.example.ConsultasMedicas.dto.AtualizarMedico;
import com.example.ConsultasMedicas.infra.exceptions.EsseIdNaoExiste;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public Medico cadastrar(Medico dados) {
        return repository.save(dados);
    }

    public List<Medico> listarMedicos() {
        return repository.findAll();
    }

    public Medico listarMedicoID(long id) {
        return repository.findById(id).orElseThrow(() -> new EsseIdNaoExiste("Médico não encontrado"));
    }

    @Transactional
    public Medico atualizar(long id, AtualizarMedico dados) {
        Medico medico = repository.findById(id).orElseThrow(() -> new EsseIdNaoExiste("Médico não encontrado"));
        medico.atualizarMedico(dados);
        return medico;
    }

    @Transactional
    public void deletar(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EsseIdNaoExiste("Esse ID não existe");
        }
    }
}
