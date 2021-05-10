package by.innowise.course.services.impl;

import by.innowise.course.config.Dispatcher;
import by.innowise.course.dto.entities.ReservationDto;
import by.innowise.course.entities.Reservation;
import by.innowise.course.entities.Room;
import by.innowise.course.entities.types.ReservationStatus;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.ReservationMapper;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.ReservationRepository;
import by.innowise.course.services.ReservationService;
import by.innowise.course.validator.Validator;
import by.innowise.course.validator.type.ValidatorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final BaseRepository<Reservation> reservationRepository;
    private final Dispatcher<ValidatorType, Validator> validatorDispatcher;

    public ReservationServiceImpl(ReservationRepository reservationRepository, Dispatcher<ValidatorType, Validator> validatorDispatcher) {
        this.reservationRepository = reservationRepository;
        this.validatorDispatcher = validatorDispatcher;
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

    @Transactional
    @Override
    public ReservationDto cancel(Long reservationId) {
        // TODO: 03/03/2021 validator "is reservation contains this user?"
        Reservation reservation = ReservationMapper.INSTANCE
                .reservationDtoToReservation(findById(reservationId));
        validatorDispatcher.getByName(ValidatorType.CANCEL_RESERVATION_VALIDATOR).validate(reservation);
        reservation.setStatus(ReservationStatus.CANCELED);
        return save(ReservationMapper.INSTANCE.reservationToReservationDto(reservation));
    }

    @Transactional
    @Override
    public ReservationDto add(Long hotelId, Long roomId, ReservationDto reservationDto) {
        // TODO: 02/03/2021 validator
        Reservation reservation = ReservationMapper.INSTANCE.reservationDtoToReservation(reservationDto);
        Room room = new Room();
        room.setId(roomId);
        reservation.setRoom(room);
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservation.setApplicationDate(LocalDate.now());
        validatorDispatcher.getByName(ValidatorType.ADD_RESERVATION_VALIDATOR).validate(reservation);
        return save(ReservationMapper.INSTANCE.reservationToReservationDto(reservation));
    }
}
