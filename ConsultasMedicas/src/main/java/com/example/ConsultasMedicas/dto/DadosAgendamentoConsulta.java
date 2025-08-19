package com.example.ConsultasMedicas.dto;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
    Long idPaciente,
    Long idMedico,
    String descricao,
    LocalDateTime data
) { }
