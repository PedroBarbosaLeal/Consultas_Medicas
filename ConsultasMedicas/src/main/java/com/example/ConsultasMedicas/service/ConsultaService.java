package com.example.ConsultasMedicas.service;

import com.example.ConsultasMedicas.domain.Consulta;
import com.example.ConsultasMedicas.domain.Medico;
import com.example.ConsultasMedicas.domain.Paciente;
import com.example.ConsultasMedicas.domain.repository.ConsultaRepository;
import com.example.ConsultasMedicas.domain.repository.MedicoRepository;
import com.example.ConsultasMedicas.domain.repository.PacienteRepository;
import com.example.ConsultasMedicas.dto.AtualizarDataConsulta;
import com.example.ConsultasMedicas.dto.DadosAgendamentoConsulta;
import com.example.ConsultasMedicas.infra.exceptions.EsseIdNaoExiste;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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

        Consulta consultaSalva = consultaRepository.save(novaConsulta);

        DateTimeFormatter formatar = DateTimeFormatter.ofPattern(
                "dd 'de' MMMM 'de' yyyy, 'às' HH:mm",
                new Locale("pt", "BR")
        );

        String dataFormatada = consulta.data().format(formatar);

        String assunto = "Confirmação de Agendamento de Consulta";

        String mensagem = "Olá, " + paciente.getNome() + "!\n\n"
                + "Sua consulta foi agendada com sucesso. Agradecemos a sua preferência.\n\n"
                + "Abaixo estão os detalhes do seu agendamento:\n\n"
                + "* Médico(a): " + medico.getNome() + "\n"
                + "* Data: " + dataFormatada + "\n"
                + "* Descrição: " + consultaSalva.getDescricao() + "\n\n"
                + "Aguardamos você.\n\n"
                + "Atenciosamente,\n"
                + "Equipe de Agendamento";

        emailService.enviarEmailTexto(paciente.getEmail(), assunto, mensagem);

        return consultaSalva;
    }

    public List<Consulta> listarConsultaPorIdPaciente(Long idPaciente) {

        return consultaRepository.findByPacienteId(idPaciente);
    }

    public List<Consulta> listarConsultaPorIdMedico(Long idMedico) {
        return consultaRepository.findByMedicoId_medico(idMedico);
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
