package com.oppo.oppo.Controller;

import com.oppo.oppo.Model.MailStructure;
import com.oppo.oppo.Service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure) throws MessagingException {
        mailService.sendEmail(mail, mailStructure);
        return "Successfully sent the mail !!";
    }
}
