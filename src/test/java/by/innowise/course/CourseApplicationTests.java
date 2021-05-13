package by.innowise.course;

import by.innowise.course.entities.User;
import by.innowise.course.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@SpringBootTest
@ContextConfiguration(initializers = {CourseApplicationTests.Initializer.class})
class CourseApplicationTests {

    private final UserRepository userRepository;

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("course-work")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    CourseApplicationTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + container.getJdbcUrl(),
                    "spring.datasource.username=" + container.getUsername(),
                    "spring.datasource.password=" + container.getPassword(),
                    "spring.liquibase.enabled=true"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    public void isCorrectUsersDbSize() {
        List<User> userList = userRepository.findAll();
        Assertions.assertEquals(userList.size(), 0);
    }

}
