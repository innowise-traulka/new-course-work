package by.innowise.course.entities;

import by.innowise.course.entities.types.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First name must me not blank")
    @Size(min = 2, max = 50, message = "First name size must be more than 2 and less than 50")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name must be not blank")
    @Size(min = 2, max = 50, message = "First name size must be more than 2 and less than 50")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Patronymic must be not blank")
    @Size(min = 2, max = 50, message = "First name size must be more than 2 and less than 50")
    @Column(name = "patronymic")
    private String patronymic;

    @Email(message = "Incorrect email")
    @NotBlank(message = "Email must be not blank")
    @Size(max = 100, message = "Email size must be less than 101 symbol")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Password must be not blank")
    @Size(min = 8, message = "Password must be more than 8 symbols")
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.getName().name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userStatus != UserStatus.BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userStatus == UserStatus.ACTIVE;
    }
}
