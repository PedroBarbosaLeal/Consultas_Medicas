package com.example.ConsultasMedicas.dto;

import com.example.ConsultasMedicas.domain.Enum.TipoPagamento;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
    Long idPaciente,
    Long idMedico,
    TipoPagamento tipoPagamento,
    String descricao,
    LocalDateTime data
) { }
