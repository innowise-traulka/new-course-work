package by.innowise.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Value("${app.accountConfirmURL}")
    private String accountConfirmURL;

    @Value("${app.passwordResetURL}")
    private String passwordResetURL;

    @Value("${spring.mail.username}")
    private String setFromMail;

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMessageForConfirmAccount(final String email, final String token) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFromMail);
        helper. setTo(email);

        String subject = "Here's the link to confirm your account";
        String content =
                "<p>Hello,</p>"
                        + "<p>You have requested to create new account.</p>"
                        + "<p>Click the link below to active your account:</p>"
                        + "<p><a href=\""
                        + accountConfirmURL
                        + token
                        + "\">Confirm my account</a></p>"
                        + "<br>"
                        + "<p>Ignore this email if you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    public void sendMessageForResetPassword(final String email, final String token) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFromMail);
        helper.setTo(email);

        String subject = "Here's the link to reset your password";
        String content =
                "<p>Hello,</p>"
                        + "<p>You have requested to reset password.</p>"
                        + "<p>Click the link below to reset your password:</p>"
                        + "<p><a href=\""
                        + passwordResetURL
                        + token
                        + "\">Reset my password</a></p>"
                        + "<br>"
                        + "<p>Ignore this email if you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
