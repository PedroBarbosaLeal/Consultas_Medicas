package com.example.ConsultasMedicas.controller;

import com.example.ConsultasMedicas.domain.Consulta;
import com.example.ConsultasMedicas.domain.Medico;
import com.example.ConsultasMedicas.domain.Paciente;
import com.example.ConsultasMedicas.dto.AtualizarDataConsulta;
import com.example.ConsultasMedicas.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/Consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService repository;

    @PostMapping
    public ResponseEntity<String> criarConsulta(@Valid @RequestBody Consulta consulta) {
        Consulta consultaCriada = repository.criarConsulta(consulta);

        URI url = URI.create("/Consultas/" + consultaCriada.getId_consulta());

        return ResponseEntity.status(HttpStatus.CREATED).body("Consulta Marcada: " + consultaCriada.getId_consulta() + " " + consultaCriada);
    }

    @GetMapping("/medicos/{id}")
    public ResponseEntity<Medico> listarConsultasApartirDoMedico(@PathVariable Long id) {
        return ResponseEntity.ok(repository.listarConsultaPorIdMedico(id));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<Paciente> listarConsultasApartirDoPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(repository.listarConsultaPorIdPaciente(id));
    }

    @PutMapping
    public ResponseEntity<Consulta> atualizarDataDaConsulta(@PathVariable Long id, AtualizarDataConsulta dataConsulta) {
        Consulta consulta = repository.atualizarADataDaConsulta(id, dataConsulta);

        return ResponseEntity.ok(consulta);
    }
}
