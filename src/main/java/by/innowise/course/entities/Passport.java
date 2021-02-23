package by.innowise.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "passport")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passport implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Digits(integer = 7, fraction = 0, message = "House number must be less than 8 symbols")
    @Column(name = "number")
    private Integer number;

    @NotBlank
    @Size(max = 2, message = "Serial must be less than 3 symbols")
    @Column(name = "serial")
    private String serial;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "issuedAt")
    private LocalDate issuedAt;
}
