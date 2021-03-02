package by.innowise.course.services.impl;

import by.innowise.course.dto.entities.HotelDto;
import by.innowise.course.entities.Hotel;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.HotelMapper;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.HotelRepository;
import by.innowise.course.services.CategoryService;
import by.innowise.course.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    private final BaseRepository<Hotel> hotelRepository;
    private final CategoryService categoryService;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, CategoryService categoryService) {
        this.hotelRepository = hotelRepository;
        this.categoryService = categoryService;
    }

    @Override
    public HotelDto save(HotelDto hotelDto) {
        Hotel hotel = HotelMapper.INSTANCE.hotelDtoToHotel(hotelDto);
        hotel.getAddress().setHotel(hotel);
        return HotelMapper.INSTANCE.hotelToHotelDto(hotelRepository.save(hotel));
    }

    @Override
    public HotelDto findById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() ->
                new ApiException("Hotel not found"));
        return HotelMapper.INSTANCE.hotelToHotelDto(hotel);
    }
}
