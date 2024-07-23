package ru.example.spring.hotel.booking.web.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelResponseDto {
    private Long id;
    private String name;
    private String title;
    private String locationCity;
    private String locationAddress;
    private Integer distanceFromCity;
    private Integer rating;
    private Integer ratingsCount;
}
