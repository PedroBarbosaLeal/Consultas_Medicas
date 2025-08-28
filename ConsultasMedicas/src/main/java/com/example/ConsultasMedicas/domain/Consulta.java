package com.example.ConsultasMedicas.domain;

import com.example.ConsultasMedicas.domain.Enum.TipoPagamento;
import com.example.ConsultasMedicas.dto.AtualizarDataConsulta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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


    @Enumerated(EnumType.STRING)
    @NotNull(message = "É necessário escolher uma forma de pagamento para a consulta!")
    @Column(nullable = false, columnDefinition = "varchar(255) default 'PIX'")
    private TipoPagamento tipoPagamento;


    @Column(nullable = false, precision = 10, scale = 2, columnDefinition = "NUMERIC(10, 2) default 0.00")
    private BigDecimal valorConsulta;

    @ManyToOne
    @NotNull(message = "É obrigatório ter um paciente na consulta")
    @JoinColumn(name = "id_medico")
    private Medico medico;

    public BigDecimal getValorConsulta() {
        return valorConsulta;
    }
    public void setValorConsulta(BigDecimal valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

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
