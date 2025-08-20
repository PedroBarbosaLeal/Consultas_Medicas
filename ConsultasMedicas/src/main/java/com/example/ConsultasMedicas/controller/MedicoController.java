package com.example.ConsultasMedicas.controller;

import com.example.ConsultasMedicas.domain.Medico;
import com.example.ConsultasMedicas.dto.AtualizarMedico;
import com.example.ConsultasMedicas.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Medicos")
public class MedicoController {

    @Autowired
    private MedicoService repository;

    @PostMapping
    public ResponseEntity<String> cadastrar(@Valid @RequestBody Medico dados) {
        Medico medico = repository.cadastrar(dados);

        URI url = URI.create("/Medicos/" + medico.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body("Medico cadastrado: " + medico.getId() + " " + medico);
    }

    @GetMapping
    public ResponseEntity<List<Medico>> ListarMedicos() {
        List<Medico> medico = repository.listarMedicos();

        return ResponseEntity.status(HttpStatus.OK).body(medico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> ListarMedicoId(@PathVariable Long id) {
        return ResponseEntity.ok(repository.listarMedicoID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMedicos(@PathVariable Long id, @RequestBody @Valid AtualizarMedico dados) {
        Medico medico = repository.atualizar(id, dados);

        return ResponseEntity.ok(medico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarMedico(@PathVariable Long id) {
        repository.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
