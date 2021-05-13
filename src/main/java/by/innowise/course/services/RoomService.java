package by.innowise.course.services;

import by.innowise.course.dto.entities.RoomDto;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface RoomService extends BaseService<RoomDto> {
    RoomDto save(RoomDto roomDto);

    List<RoomDto> findAll();

    List<RoomDto> findAllPaging(Integer page, Integer size, String sort);

    @Transactional
    RoomDto add(Long hotelId, Long categoryId, RoomDto roomDto);
}
