package by.innowise.course.entities;

import by.innowise.course.entities.types.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "application_date")
    private LocalDate applicationDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
