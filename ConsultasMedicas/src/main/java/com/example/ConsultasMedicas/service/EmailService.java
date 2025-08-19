package com.example.ConsultasMedicas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender JavaMailSender;

    public String enviarEmailTexto(String destinatario, String assunto, String msn){
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom("pedroleal969@gmail.com");
        mensagem.setTo(destinatario);
        mensagem.setSubject(assunto);
        mensagem.setText(msn);
        JavaMailSender.send(mensagem);
        return "Email Enviado";
    }
}
