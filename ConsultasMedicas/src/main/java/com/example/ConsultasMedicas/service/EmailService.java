package com.example.ConsultasMedicas.service;

import com.example.ConsultasMedicas.domain.Consulta;
import com.example.ConsultasMedicas.domain.Medico;
import com.example.ConsultasMedicas.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender JavaMailSender;

    private String assuntoEmail = "Confirmação de Agendamento de Consulta";

    public String enviarEmailTexto(String destinatario, String msn){
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom("pedroleal969@gmail.com");
        mensagem.setTo(destinatario);
        mensagem.setSubject(assuntoEmail);
        mensagem.setText(msn);
        JavaMailSender.send(mensagem);
        return "Email Enviado";
    }

    public String formatarMensagem(Paciente paciente, Medico medico, Consulta consulta){
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern(
                "dd 'de' MMMM 'de' yyyy, 'às' HH:mm",
                new Locale("pt", "BR")
        );

        String dataFormatada = consulta.getData().format(formatar);

        String mensagem = "Olá, " + paciente.getNome() + "!\n\n"
                + "Sua consulta foi agendada com sucesso. Agradecemos a sua preferência.\n\n"
                + "Abaixo estão os detalhes do seu agendamento:\n\n"
                + "* Médico(a): " + medico.getNome() + "\n"
                + "* Data: " + dataFormatada + "\n"
                + "* Tipo de pagamento: " + consulta.getTipoPagamento() + "\n"
                + "* Valor do pagamento: " + "R$"+consulta.getValorConsulta() + "\n"
                + "* Descrição: " + consulta.getDescricao() + "\n\n"
                + "Aguardamos você.\n\n"
                + "Atenciosamente,\n"
                + "Equipe de Agendamento";

        return mensagem;
    }
}
