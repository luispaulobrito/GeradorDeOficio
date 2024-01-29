package com.example.geradorback.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendPasswordResetEmail(String recipient, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("Gerador de Oficio <geradordeoficio@gmail.com>");
            helper.setTo(recipient);
            helper.setSubject("Redefinição de senha");

            String text = "<html>"
                    + "<body style='font-family: Arial, sans-serif;'>"
                    + "<h1>Recuperação de senha</h1>"
                    + "<p>Olá, tudo bem?</p>"
                    + "<p>Você solicitou a redefinição de senha do e-mail cadastrado em nosso sistema. Clique no link abaixo para prosseguir:</p>"
                    + "<p style='background-color: #24a0ed; padding: 10px; color: white; border-radius: 5px; display: inline-block;'><a href='http://localhost:4200/auth/new-password/"
                    + recipient + "/"
                    + token + "' style='color: white; text-decoration: none;'>Redefinir senha</a></p>"
                    + "</body>"
                    + "</html>";

            helper.setText(text, true);

            javaMailSender.send(message);

            logger.info("E-mail de redefinição de senha enviado para: {}", recipient);

        } catch (MessagingException e) {
            logger.error("Erro ao enviar e-mail de redefinição de senha para: {}", recipient, e);
        }
    }
}
