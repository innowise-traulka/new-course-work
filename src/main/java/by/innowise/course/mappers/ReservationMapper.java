package by.innowise.course.mappers;

import by.innowise.course.dto.entities.ReservationDto;
import by.innowise.course.dto.entities.RoomDto;
import by.innowise.course.entities.Reservation;
import by.innowise.course.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    ReservationDto reservationToReservationDto(Reservation reservation);

    Reservation reservationDtoToReservation(ReservationDto reservationDto);
}
