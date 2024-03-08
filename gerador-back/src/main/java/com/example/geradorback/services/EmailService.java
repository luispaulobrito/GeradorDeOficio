package com.example.geradorback.services;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailSender;
    @Value("reset_password_email.html")
    private String emailTemplate;
    @Autowired
    private Configuration config;
    private final JavaMailSender javaMailSender;
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendPasswordResetEmail(String id, String recipient, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setFrom("Gerador de Oficio <" + emailSender + ">");
            helper.setTo(recipient);
            helper.setSubject("Redefinição de senha");

            Map<String, Object> model = new HashMap<>();
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(config.getTemplate(emailTemplate), model);
            text = text.replace("{id}", id).replace("{token}", token);
            helper.setText(text, true);

            javaMailSender.send(message);
            logger.info("E-mail de redefinição de senha enviado para: {}", recipient);
        } catch (MessagingException | IOException | TemplateException e) {
            logger.error("Erro ao enviar e-mail de redefinição de senha para: {}", recipient, e);
        }
    }
}
