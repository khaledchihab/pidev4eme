package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.DTO.EmailDTO;
import com.example.PlateformeMobilite.DTO.UserResponseDTO;
import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.Moyenne;
import com.example.PlateformeMobilite.Interfaces.IEmailService;
import com.example.PlateformeMobilite.Interfaces.UserClient;
import com.example.PlateformeMobilite.Repository.FormRepository;
import com.example.PlateformeMobilite.Repository.MoyenneRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {

    private final JavaMailSender javaMailSender;
    private final MoyenneRepository ms;
    private final FormRepository fs;
    private final UserClient userClient; // Feign client

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Override
    public List<EmailDTO> getAcceptedUsers(Long formId) {
        Form form = fs.findByFormId(formId);
        int placesDisp = form.getPlacesDisp();

        List<Moyenne> moyennes = ms.findAllByForm_FormId(formId);

        // Sort by descending moyenne
        moyennes.sort(Comparator.comparingDouble(Moyenne::getMoyenne).reversed());

        List<EmailDTO> acceptedUsers = new ArrayList<>();
        for (int i = 0; i < Math.min(placesDisp, moyennes.size()); i++) {
            Moyenne moyenne = moyennes.get(i);

            // Fetch user info from Authentication Service
            UserResponseDTO user = userClient.getUserById(moyenne.getUserId());

            acceptedUsers.add(new EmailDTO(
                    user.getId(),
                    user.getUsername(),
                    form.getFormId(),
                    form.getFormName(),
                    user.getEmail()
            ));
        }

        return acceptedUsers;
    }

    @Override
    public List<EmailDTO> getRejectedUsers(Long formId) {
        Form form = fs.findByFormId(formId);
        int placesDisp = form.getPlacesDisp();
        List<Moyenne> moyennes = ms.findAllByForm_FormId(formId);

        // Sort by descending moyenne
        moyennes.sort(Comparator.comparingDouble(Moyenne::getMoyenne).reversed());

        List<EmailDTO> acceptedUsers = getAcceptedUsers(formId);

        // Filter rejected users
        List<EmailDTO> rejectedUsers = moyennes.stream()
                .filter(m -> acceptedUsers.stream().noneMatch(a -> a.getUserId().equals(m.getUserId())))
                .map(m -> {
                    UserResponseDTO user = userClient.getUserById(m.getUserId());
                    return new EmailDTO(
                            user.getId(),
                            user.getUsername(),
                            form.getFormId(),
                            form.getFormName(),
                            user.getEmail()
                    );
                })
                .collect(Collectors.toList());

        return rejectedUsers;
    }

    @Override
    public void sendAcceptanceEmail(EmailDTO emailDTO) {
        String subject = "Congratulations!";
        String message = "Dear " + emailDTO.getUsername() + ",\n\n"
                + "You have been accepted for the Form: " + emailDTO.getFormName() + ".\n"
                + "Congratulations and best wishes!";
        sendEmail(emailDTO.getEmail(), subject, message);
    }

    @Override
    public void sendRejectionEmail(EmailDTO emailDTO) {
        String subject = "Application Update";
        String message = "Dear " + emailDTO.getUsername() + ",\n\n"
                + "We regret to inform you that your application for the Form: " + emailDTO.getFormName() + " has been rejected.\n"
                + "Thank you for applying, and we appreciate your interest.";
        sendEmail(emailDTO.getEmail(), subject, message);
    }

    @Override
    public void sendEmailsToAcceptedAndRejectedUsers(Long formId) {
        List<EmailDTO> acceptedUsers = getAcceptedUsers(formId);
        List<EmailDTO> rejectedUsers = getRejectedUsers(formId);

        acceptedUsers.forEach(this::sendAcceptanceEmail);
        rejectedUsers.forEach(this::sendRejectionEmail);
    }
}
