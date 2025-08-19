package com.example.ConsultasMedicas.controller;

import com.example.ConsultasMedicas.domain.Consulta;
import com.example.ConsultasMedicas.domain.Paciente;
import com.example.ConsultasMedicas.domain.repository.PacienteRepository;
import com.example.ConsultasMedicas.dto.AtualizarDataConsulta;
import com.example.ConsultasMedicas.dto.DadosAgendamentoConsulta;
import com.example.ConsultasMedicas.infra.exceptions.EsseIdNaoExiste;
import com.example.ConsultasMedicas.service.ConsultaService;
import com.example.ConsultasMedicas.service.EmailService;
import com.example.ConsultasMedicas.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService repository;

    @Autowired
    public EmailService email;

    @Autowired
    public PacienteRepository pacienteRepository;


    @PostMapping
    public ResponseEntity<String> criarConsulta(@Valid @RequestBody DadosAgendamentoConsulta consulta) {

        Consulta consultaCriada = repository.agendarConsulta(consulta);

        URI url = URI.create("/Consultas/" + consultaCriada.getId_consulta());

        return ResponseEntity.status(HttpStatus.CREATED).body("Consulta Marcada: " + consultaCriada.getId_consulta() + " " + consultaCriada);
    }

    @GetMapping("/medicos/{id}")
    public ResponseEntity<List<Consulta>> listarConsultasApartirDoMedico(@PathVariable Long id) {
        return ResponseEntity.ok(repository.listarConsultaPorIdMedico(id));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Consulta>> listarConsultasApartirDoPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(repository.listarConsultaPorIdPaciente(id));
    }

    @GetMapping("/DataAsc")
    public ResponseEntity<List<Consulta>> listarConsultasPorDataAsc() {
        return ResponseEntity.ok(repository.listarConsultaPorDataAsc());
    }

    @GetMapping("/DataDesc")
    public ResponseEntity<List<Consulta>> listarConsultasPorDataDesc() {
        return ResponseEntity.ok(repository.listarConsultaPorDataDesc());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizarDataDaConsulta(@PathVariable Long id, @RequestBody @Valid AtualizarDataConsulta dataConsulta) {
        Consulta consulta = repository.atualizarADataDaConsulta(id, dataConsulta);

        return ResponseEntity.ok(consulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarConsulta(@PathVariable Long id) {
        repository.deletarConsulta(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
