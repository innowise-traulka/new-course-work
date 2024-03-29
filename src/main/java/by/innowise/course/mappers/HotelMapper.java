package by.innowise.course.mappers;

import by.innowise.course.dto.entities.HotelDto;
import by.innowise.course.entities.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    HotelDto hotelToHotelDto(Hotel hotel);

    Hotel hotelDtoToHotel(HotelDto hotelDto);
}
