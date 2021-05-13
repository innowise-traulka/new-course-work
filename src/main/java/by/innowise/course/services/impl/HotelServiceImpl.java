package by.innowise.course.services.impl;

import by.innowise.course.dto.entities.HotelDto;
import by.innowise.course.entities.Hotel;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.HotelMapper;
import by.innowise.course.mappers.UserMapper;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.HotelRepository;
import by.innowise.course.services.CategoryService;
import by.innowise.course.services.HotelService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelServiceImpl implements HotelService {
    private final BaseRepository<Hotel> hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Transactional
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

    @Override
    public List<HotelDto> findAll() {
        return hotelRepository.findAll().stream().map(HotelMapper.INSTANCE::hotelToHotelDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDto> findAllPaging(Integer page, Integer size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return hotelRepository.findAll(pageable).stream().map(HotelMapper.INSTANCE::hotelToHotelDto)
                .collect(Collectors.toList());
    }
}
