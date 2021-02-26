package by.innowise.course.services;

import by.innowise.course.dto.entities.HotelDto;
import by.innowise.course.entities.Hotel;

public interface HotelService extends BaseService<Hotel> {

    HotelDto save(HotelDto hotelDto);
}
