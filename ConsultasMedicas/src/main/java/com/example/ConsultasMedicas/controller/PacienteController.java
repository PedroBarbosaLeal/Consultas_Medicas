package com.example.ConsultasMedicas.controller;

import com.example.ConsultasMedicas.domain.Paciente;
import com.example.ConsultasMedicas.dto.AtualizarPaciente;
import com.example.ConsultasMedicas.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Pacientes")
public class PacienteController {

    @Autowired
    private PacienteService repository;

    @PostMapping
    public ResponseEntity<String> cadastrar(@Valid @RequestBody Paciente dados) {
        Paciente paciente = repository.CadastrarPaciente(dados);

        URI url = URI.create("/Pacientes/" + paciente.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body("Paciente Cadastrado: " + paciente.getId() + " " + paciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> paciente = repository.listarPaciente();
        return ResponseEntity.status(HttpStatus.OK).body(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listarPorID(@PathVariable Long id) {
        return ResponseEntity.ok(repository.listarPacienteId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizarPaciente dados) {
        Paciente paciente = repository.atualizar(id, dados);
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        repository.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
