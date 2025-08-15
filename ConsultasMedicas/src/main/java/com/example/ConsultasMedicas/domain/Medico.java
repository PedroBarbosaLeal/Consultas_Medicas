package com.example.ConsultasMedicas.domain;

import com.example.ConsultasMedicas.domain.Enum.Especialidade;
import com.example.ConsultasMedicas.dto.AtualizarMedico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Medico")
public class Medico {

    public void atualizarMedico(AtualizarMedico medico) {
        this.especialidade = medico.especialidade();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_medico;

    @Column(unique = true)
    @NotBlank(message = "Nome Obrigatorio")
    private String nome;

    @Column(unique = true)
    @NotBlank(message = "CRM Obrigatorio")
    private String crm;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Especialidade Obrigatoria")
    private Especialidade especialidade;

}
