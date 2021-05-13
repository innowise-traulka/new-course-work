package by.innowise.course.services;

import by.innowise.course.dto.entities.ReservationDto;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface ReservationService extends BaseService<ReservationDto> {
    ReservationDto cancel(Long reservationId);

    @Transactional
    ReservationDto confirm(Long reservationId);

    List<ReservationDto> findAll();

    List<ReservationDto> findAllPaging(Integer page, Integer size, String sort);

    @Transactional
    ReservationDto add(Long hotelId, Long roomId, ReservationDto reservationDto);
}
