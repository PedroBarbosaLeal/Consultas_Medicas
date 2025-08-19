package com.example.ConsultasMedicas.domain;

import com.example.ConsultasMedicas.dto.AtualizarDataConsulta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Consulta")
public class Consulta {

    public Consulta(Paciente paciente, Medico medico, LocalDateTime data, String descricao) {
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.descricao = descricao;
    }

    public void AtualizarData(AtualizarDataConsulta dados){
        this.data = dados.data();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_consulta;

    @NotNull(message = "Data obrigatoria")
    @Future(message = "A data tem que ser no futuro")
    private LocalDateTime data;

    @NotBlank(message = "Descricao obrigatoria")
    private String descricao;

    @ManyToOne
    @NotNull(message = "É obrigatório ter um médico na consulta")
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @NotNull(message = "É obrigatório ter um paciente na consulta")
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @Override
    public String toString() {
        return "Consulta{" +
                "id_consulta=" + id_consulta +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                ", paciente=" + paciente.getNome() +
                ", medico=" + medico.getNome() +
                '}';
    }
}
