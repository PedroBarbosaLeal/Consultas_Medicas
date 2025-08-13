package com.example.ConsultasMedicas.dto;

import com.example.ConsultasMedicas.domain.Enum.Especialidade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public record AtualizarMedico(
        @Enumerated(EnumType.STRING)
        @NotBlank(message = "Campo obrigatório")
        Especialidade especialidade) {
}
