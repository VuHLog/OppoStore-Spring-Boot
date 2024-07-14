package com.oppo.oppo.Service;

import com.oppo.oppo.Model.MailStructure;
import jakarta.mail.MessagingException;

public interface MailService {
    public void sendEmail(String mail, MailStructure mailStructure) throws MessagingException;
}
