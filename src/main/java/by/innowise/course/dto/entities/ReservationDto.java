package by.innowise.course.dto.entities;

import by.innowise.course.entities.types.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate applicationDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    private ReservationStatus status;
    private Long roomId;
}
