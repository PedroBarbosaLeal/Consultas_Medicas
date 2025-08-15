package com.example.ConsultasMedicas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AtualizarDataConsulta(

        @NotNull(message = "Data obrigatoria")
        @Future(message = "A data tem que ser no futuro")
        LocalDateTime data
) {
}
