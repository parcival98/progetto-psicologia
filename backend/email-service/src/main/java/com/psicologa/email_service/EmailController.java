package com.psicologa.email_service;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender; // Strumento di Spring Boot

    @PostMapping("/send")
    public String mandaEmail(@RequestBody Map<String, String> datiEmail) {
        
        // Estraiamo i dati che ci manderà il file HTML
        String destinatario = datiEmail.get("to");
        String oggetto = datiEmail.get("subject");
        String corpoTesto = datiEmail.get("body");

        // Costruiamo la lettera
        SimpleMailMessage lettera = new SimpleMailMessage();
        lettera.setFrom("studio@psicologia.it"); // Nome fittizio che compare
        lettera.setTo(destinatario);
        lettera.setSubject(oggetto);
        lettera.setText(corpoTesto);

        // Spediamo!
        mailSender.send(lettera);

        return "Email spedita con successo!";
    }
}