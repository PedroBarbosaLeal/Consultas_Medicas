package com.example.ConsultasMedicas.domain;

import com.example.ConsultasMedicas.dto.AtualizarPaciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    @Column(unique = true)
    @NotBlank(message = "Nome obritatório")
    private String nome;

    @Column(unique = true)
    @Size(max = 11,min = 11,message = "CPF invalido")
    @NotNull(message = "CPF obrigatório")
    private String cpf;

    @NotNull(message = "Data de nascimento obrigatoria")
    @Past(message = "Você não nasceu no futuro kkk")
    private LocalDate dataDeNascimento;

    public void atualizarPaciente(AtualizarPaciente dados){
        this.nome = dados.nome();
    }
}
