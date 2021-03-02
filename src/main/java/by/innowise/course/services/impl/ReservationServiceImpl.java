package by.innowise.course.services.impl;

import by.innowise.course.dto.entities.ReservationDto;
import by.innowise.course.entities.Reservation;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.ReservationMapper;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.ReservationRepository;
import by.innowise.course.services.ReservationService;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final BaseRepository<Reservation> reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationDto save(ReservationDto reservationDto) {
        Reservation reservation = ReservationMapper.INSTANCE.reservationDtoToReservation(reservationDto);
        return ReservationMapper.INSTANCE.reservationToReservationDto(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDto findById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                new ApiException("Reservation not found"));
        return ReservationMapper.INSTANCE.reservationToReservationDto(reservation);
    }
}
