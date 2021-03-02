package by.innowise.course.services;

import by.innowise.course.dto.entities.RoomDto;
import org.springframework.transaction.annotation.Transactional;

public interface RoomService extends BaseService<RoomDto> {
    RoomDto save(RoomDto roomDto);

    @Transactional
    RoomDto add(Long hotelId, Long categoryId, RoomDto roomDto);
}
