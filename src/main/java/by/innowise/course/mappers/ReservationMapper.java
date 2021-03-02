package by.innowise.course.mappers;

import by.innowise.course.dto.entities.ReservationDto;
import by.innowise.course.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(target = "roomId", source = "reservation.room.id")
    ReservationDto reservationToReservationDto(Reservation reservation);

    @Mapping(target = "room.id", source = "reservationDto.roomId")
    Reservation reservationDtoToReservation(ReservationDto reservationDto);
}
