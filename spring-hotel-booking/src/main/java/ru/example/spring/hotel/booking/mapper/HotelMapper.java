package ru.example.spring.hotel.booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import ru.example.spring.hotel.booking.model.Hotel;
import ru.example.spring.hotel.booking.web.model.request.HotelRequestDto;
import ru.example.spring.hotel.booking.web.model.response.HotelResponseDto;

import java.util.List;

import static java.util.Objects.nonNull;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HotelMapper {

    Hotel toModel(HotelRequestDto dto);

    HotelResponseDto toDto(Hotel model);

    List<HotelResponseDto> toListDto(Page<Hotel> models);

    default Hotel toUpdateModel(Hotel existsHotel, HotelRequestDto dto) {
        return Hotel.builder()
                .id(existsHotel.getId())
                .rating(existsHotel.getRating())
                .ratingsCount(existsHotel.getRatingsCount())
                .name(nonNull(dto.getName()) ? dto.getName() : existsHotel.getName())
                .title(nonNull(dto.getTitle()) ? dto.getTitle() : existsHotel.getTitle())
                .locationCity(nonNull(dto.getLocationCity()) ? dto.getLocationCity() : existsHotel.getLocationCity())
                .locationAddress(nonNull(dto.getLocationAddress()) ? dto.getLocationAddress() : existsHotel.getLocationAddress())
                .distanceFromCity(nonNull(dto.getDistanceFromCity()) ? dto.getDistanceFromCity() : existsHotel.getDistanceFromCity())
                .build();
    }

}
