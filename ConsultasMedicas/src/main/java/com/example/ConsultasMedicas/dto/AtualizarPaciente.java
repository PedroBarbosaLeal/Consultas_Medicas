package com.example.ConsultasMedicas.dto;
import jakarta.validation.constraints.NotBlank;

public record AtualizarPaciente(
        @NotBlank(message = "Nome obritatório")
        String nome
) {
}
