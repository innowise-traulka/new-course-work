package by.innowise.course.services.impl;

import by.innowise.course.dto.entities.ReservationDto;
import by.innowise.course.dto.entities.RoomDto;
import by.innowise.course.entities.Category;
import by.innowise.course.entities.Room;
import by.innowise.course.entities.UserConfirmationCode;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.ReservationMapper;
import by.innowise.course.mappers.RoomMapper;
import by.innowise.course.mappers.UserConfirmationCodeMapper;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.RoomRepository;
import by.innowise.course.services.RoomService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomServiceImpl implements RoomService {
    private final BaseRepository<Room> roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDto save(RoomDto roomDto) {
        Room room = RoomMapper.INSTANCE.roomDtoToRoom(roomDto);
        return RoomMapper.INSTANCE.roomToRoomDto(roomRepository.save(room));
    }

    @Override
    public List<RoomDto> findAll() {
        return roomRepository.findAll().stream().map(RoomMapper.INSTANCE::roomToRoomDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> findAllPaging(Integer page, Integer size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return roomRepository.findAll(pageable).stream().map(RoomMapper.INSTANCE::roomToRoomDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto findById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() ->
                new ApiException("Room not found"));
        return RoomMapper.INSTANCE.roomToRoomDto(room);
    }

    @Transactional
    @Override
    public RoomDto add(Long hotelId, Long categoryId, RoomDto roomDto) {
        // TODO: 02/03/2021 validator
        Room room = RoomMapper.INSTANCE.roomDtoToRoom(roomDto);
        Category category = new Category();
        category.setId(categoryId);
        room.setCategory(category);
        return save(RoomMapper.INSTANCE.roomToRoomDto(room));
    }
}
