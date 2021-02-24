package by.innowise.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "user_confirmation_code")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserConfirmationCode {
    @Id
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "code")
    private String code;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    private LocalDate createdAt;
}