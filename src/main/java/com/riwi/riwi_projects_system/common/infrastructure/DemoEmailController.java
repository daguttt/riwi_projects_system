package com.riwi.riwi_projects_system.common.infrastructure;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.riwi_projects_system.common.infrastructure.mails.EmailUtils;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/demos/emails")
public class DemoEmailController {

    @Autowired
    private EmailUtils emailUtils;

    @GetMapping
    public ResponseEntity<String> sendDemoEmail() {
        try {
            this.emailUtils.sendHtmlEmail(new String[] { "dagutmu667@gmail.com" },
                    "Demo Spring Boot Email", "<h1>Demo email title</h1>");
        } catch (MessagingException | RuntimeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Demo email sent");
    }

}
