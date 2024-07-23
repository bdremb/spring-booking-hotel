package ru.example.spring.hotel.booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.example.spring.hotel.booking.model.Room;
import ru.example.spring.hotel.booking.web.model.request.RoomRequestDto;
import ru.example.spring.hotel.booking.web.model.response.RoomResponseDto;

import static java.util.Objects.nonNull;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RoomMapper {

    Room toModel(RoomRequestDto dto);

    RoomResponseDto toDto(Room model);

    default Room toUpdateModel(Room existsRoom, RoomRequestDto dto) {
        return Room.builder()
                .roomId(existsRoom.getRoomId())
                .hotel(existsRoom.getHotel())
                .name(nonNull(dto.getName()) ? dto.getName() : existsRoom.getName())
                .description(nonNull(dto.getDescription()) ? dto.getDescription() : existsRoom.getDescription())
                .number(nonNull(dto.getNumber()) ? dto.getNumber() : existsRoom.getNumber())
                .price(nonNull(dto.getPrice()) ? dto.getPrice() : existsRoom.getPrice())
                .maxVisitorsCount(nonNull(dto.getMaxVisitorsCount()) ? dto.getMaxVisitorsCount() : existsRoom.getMaxVisitorsCount())
                .bookingStartDate(nonNull(dto.getBookingStartDate()) ? dto.getBookingStartDate() : existsRoom.getBookingStartDate())
                .bookingEndDate(nonNull(dto.getBookingEndDate()) ? dto.getBookingEndDate() : existsRoom.getBookingEndDate())
                .build();
    }

}
