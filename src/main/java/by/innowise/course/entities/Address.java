package by.innowise.course.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Country must be not blank")
    @Size(min = 2, max = 50, message = "Country must be more than 2 and less that 51 symbols")
    @Column(name = "country")
    private String country;

    @NotBlank(message = "Town must be not blank")
    @Size(min = 2, max = 50, message = "Town must be more than 2 and less that 51 symbols")
    @Column(name = "town")
    private String town;

    @NotBlank(message = "Street must be not blank")
    @Size(min = 2, max = 50, message = "Street must be more than 2 and less that 51 symbols")
    @Column(name = "street")
    private String street;

    @Digits(integer = 3, fraction = 0, message = "House number must be less than 4 symbols")
    @Column(name = "house_number")
    private Integer houseNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Hotel hotel;
}
