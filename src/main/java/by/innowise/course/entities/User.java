package by.innowise.course.entities;

import by.innowise.course.entities.types.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "patronymic")
    private String patronymic;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus userStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Passport passport;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserConfirmationCode userConfirmationCode;
}
