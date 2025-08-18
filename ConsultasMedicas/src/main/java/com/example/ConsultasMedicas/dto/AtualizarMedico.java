package com.example.ConsultasMedicas.dto;

import com.example.ConsultasMedicas.domain.Enum.Especialidade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record AtualizarMedico(
        @Enumerated(EnumType.STRING)
        @NotNull(message = "Campo obrigatório")
        Especialidade especialidade) {
}
