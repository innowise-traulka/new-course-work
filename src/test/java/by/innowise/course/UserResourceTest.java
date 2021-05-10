package by.innowise.course;

import by.innowise.course.dto.entities.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResourceTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getUserTest() {
        ResponseEntity<UserDto> userDtoResponseEntity = testRestTemplate
                .getForEntity("/api/user/{id}", UserDto.class, 1);

        assertThat(userDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(userDtoResponseEntity.getBody())
                .getEmail()).isEqualTo("traulko03@gmail.com");
    }

}
