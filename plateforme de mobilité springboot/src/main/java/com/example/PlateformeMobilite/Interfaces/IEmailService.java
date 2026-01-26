package com.example.PlateformeMobilite.Interfaces;

import com.example.PlateformeMobilite.DTO.EmailDTO;
import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.User;

import java.util.List;

public interface IEmailService {
    public void sendEmail(String to, String subject, String text) ;

//    List<User> getAcceptedUsers(Long formId);
//    List<User> getRejectedUsers(Long formId);

    List<EmailDTO> getAcceptedUsers(Long formId);

    List<EmailDTO> getRejectedUsers(Long formId);

//    void sendAcceptanceEmail(User user, Form form);
//    void sendRejectionEmail(User user, Form form) ;

    void sendAcceptanceEmail(EmailDTO emailDTO);

    void sendRejectionEmail(EmailDTO emailDTO);

    void sendEmailsToAcceptedAndRejectedUsers(Long formId);
}
