package com.example.ConsultasMedicas.service;
import com.example.ConsultasMedicas.domain.Consulta;
import com.example.ConsultasMedicas.domain.Medico;
import com.example.ConsultasMedicas.domain.Paciente;
import com.example.ConsultasMedicas.domain.repository.ConsultaRepository;
import com.example.ConsultasMedicas.domain.repository.MedicoRepository;
import com.example.ConsultasMedicas.domain.repository.PacienteRepository;
import com.example.ConsultasMedicas.dto.AtualizarDataConsulta;
import com.example.ConsultasMedicas.dto.DadosAgendamentoConsulta;
import com.example.ConsultasMedicas.infra.exceptions.DataDeConsultaDuplicada;
import com.example.ConsultasMedicas.infra.exceptions.EsseIdNaoExiste;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EmailService emailService;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public Consulta agendarConsulta(DadosAgendamentoConsulta consulta) {

        Paciente paciente = pacienteRepository.findById(consulta.idPaciente()).orElseThrow(() -> new EsseIdNaoExiste("Paciente não encontrado!"));
        Medico medico = medicoRepository.findById(consulta.idMedico()).orElseThrow(() -> new EsseIdNaoExiste("Medico não encontrado!"));

        Consulta novaConsulta = new Consulta(paciente, medico, consulta.data(), consulta.descricao());

        novaConsulta.setTipoPagamento(consulta.tipoPagamento());

        calcularValorDaConsulta(medico,novaConsulta);

        String msn = emailService.formatarMensagem(paciente, medico, novaConsulta);
        emailService.enviarEmailTexto(paciente.getNome(), msn);

        if (consultaRepository.existsByMedicoIdAndData(medico.getId(), novaConsulta.getData())) {
            throw new DataDeConsultaDuplicada("O agendamento falhou porque o horário já está ocupado");
        } else {
            Consulta consultaSalva = consultaRepository.save(novaConsulta);
            return consultaSalva;
        }

    }

    private void calcularValorDaConsulta(Medico medico, Consulta consulta){
        double valor = medico.getEspecialidade().getValor();
        double valorComDesconto = consulta.getTipoPagamento().aplicarDesconto(valor);

        consulta.setValorConsulta(BigDecimal.valueOf(valorComDesconto));
    }

    public List<Consulta> listarConsultaPorIdPaciente(Long idPaciente) {

        return consultaRepository.findByPacienteId(idPaciente);
    }

    public List<Consulta> listarConsultaPorIdMedico(Long idMedico) {
        return consultaRepository.findByMedicoId(idMedico);
    }

    public List<Consulta> listarConsultaPorDataAsc() {
        return consultaRepository.findAllByOrderByDataAsc();
    }

    public List<Consulta> listarConsultaPorDataDesc() {
        return consultaRepository.findAllByOrderByDataDesc();
    }

    @Transactional
    public Consulta atualizarADataDaConsulta(Long idConsulta, AtualizarDataConsulta dados) {
        Consulta consulta = consultaRepository.findById(idConsulta).orElseThrow(() -> new EsseIdNaoExiste("Essa consulta não existe"));
        consulta.AtualizarData(dados);
        return consulta;
    }

    @Transactional
    public void deletarConsulta(Long idConsulta) {
        if (consultaRepository.existsById(idConsulta)) {
            consultaRepository.deleteById(idConsulta);
        } else {
            throw new EsseIdNaoExiste("Esse ID de Consulta não existe");
        }
    }
}
