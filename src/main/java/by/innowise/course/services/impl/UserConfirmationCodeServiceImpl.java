package by.innowise.course.services.impl;

import by.innowise.course.entities.UserConfirmationCode;
import by.innowise.course.repositories.UserConfirmationCodeRepository;
import by.innowise.course.services.UserConfirmationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Random;

@Service
public class UserConfirmationCodeServiceImpl implements UserConfirmationCodeService {
    private final UserConfirmationCodeRepository userConfirmationCodeRepository;

    private static final int BYTES_LENGTH = 24;

    @Autowired
    public UserConfirmationCodeServiceImpl(UserConfirmationCodeRepository userConfirmationCodeRepository) {
        this.userConfirmationCodeRepository = userConfirmationCodeRepository;
    }

    @Override
    public UserConfirmationCode createConfirmationCode() {
        String confirmationCode = generateConfirmationCode();
        UserConfirmationCode userConfirmationCode = new UserConfirmationCode();
        userConfirmationCode.setCode(confirmationCode);
        userConfirmationCode.setCreatedAt(LocalDate.now());
        return userConfirmationCode;
    }

    private String generateConfirmationCode() {
        byte[] randomBytes = new byte[BYTES_LENGTH];
        new Random().nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }
}
