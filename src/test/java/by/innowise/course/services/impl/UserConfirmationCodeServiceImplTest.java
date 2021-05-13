package by.innowise.course.services.impl;

import by.innowise.course.entities.UserConfirmationCode;
import by.innowise.course.mappers.UserConfirmationCodeMapper;
import by.innowise.course.repositories.UserConfirmationCodeRepository;
import by.innowise.course.services.UserConfirmationCodeService;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@RunWith(JUnit4.class)
public class UserConfirmationCodeServiceImplTest {

    @Mock
    private UserConfirmationCodeRepository repository;

    @InjectMocks
    private UserConfirmationCodeService userConfirmationCodeService;

    @Before
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void save() {
        String code = UUID.randomUUID().toString();
        UserConfirmationCode confirmationCode = new UserConfirmationCode();
        confirmationCode.setCode(code);
        Mockito.when(repository.save(Mockito.any(UserConfirmationCode.class))).thenReturn(confirmationCode);
        Assert.assertEquals(UserConfirmationCodeMapper.INSTANCE.codeToCodeDto(confirmationCode),
                userConfirmationCodeService.save(UserConfirmationCodeMapper.INSTANCE
                        .codeToCodeDto(confirmationCode)));
    }
}