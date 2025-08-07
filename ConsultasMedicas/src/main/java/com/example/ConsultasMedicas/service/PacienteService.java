package com.example.ConsultasMedicas.service;
import ch.qos.logback.core.joran.event.BodyEvent;
import com.example.ConsultasMedicas.domain.Paciente;
import com.example.ConsultasMedicas.dto.AtualizarPaciente;
import com.example.ConsultasMedicas.infra.exceptions.EsseIdNaoExiste;
import com.example.ConsultasMedicas.domain.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository){
        this.repository = repository;
    }

    public void CadastrarPaciente(Paciente dados){
        repository.save(dados);
    }

    public void listarPaciente(){
        repository.findAll();
    }

    public Boolean listarPacienteId(Long id){
        if(repository.existsById(id)){
           repository.findById(id);
           return true;
        }else{
            throw new EsseIdNaoExiste("ID não encontrado");
        }
    }

    @Transactional
    public Boolean deletar(Long id){
        if(repository.existsById(id)){
            repository.findById(id);
            return true;
        }else{
            throw new EsseIdNaoExiste("ID não encontrado");
        }
    }

    @Transactional
    public Boolean atualizar(Long id, AtualizarPaciente dados){
        if(repository.existsById(id)){
            Paciente paciente = repository.findById(id).get();
            paciente.atualizarPaciente(dados);
            return true;
        }else{
            throw new EsseIdNaoExiste("ID não encontrado");
        }
    }
}
