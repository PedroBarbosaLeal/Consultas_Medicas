package com.example.ConsultasMedicas.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obritatório")
    private String nome;

    @Size(max = 11,min = 11,message = "CPF invalido")
    @NotNull(message = "CPF obrigatório")
    private String cpf;

    @NotBlank
    private String dataDeNascimento;
}
