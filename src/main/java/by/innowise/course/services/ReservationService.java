package by.innowise.course.services;

import by.innowise.course.dto.entities.ReservationDto;
import org.springframework.transaction.annotation.Transactional;

public interface ReservationService extends BaseService<ReservationDto> {
    ReservationDto cancel(Long reservationId);

    @Transactional
    ReservationDto add(Long hotelId, Long roomId, ReservationDto reservationDto);
}
