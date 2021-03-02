package by.innowise.course.mappers;

import by.innowise.course.dto.entities.RoomDto;
import by.innowise.course.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(target = "categoryId", source = "room.category.id")
    RoomDto roomToRoomDto(Room room);

    @Mapping(target = "category.id", source = "roomDto.categoryId")
    Room roomDtoToRoom(RoomDto roomDto);
}
