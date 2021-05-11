package by.innowise.course.services;

import by.innowise.course.dto.entities.HotelDto;

import java.util.List;

public interface HotelService extends BaseService<HotelDto> {

    HotelDto save(HotelDto hotelDto);

    HotelDto findById(Long id);

    List<HotelDto> findAll();
}
