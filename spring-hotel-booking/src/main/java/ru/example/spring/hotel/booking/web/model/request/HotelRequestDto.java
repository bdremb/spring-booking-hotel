package ru.example.spring.hotel.booking.web.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelRequestDto {
    private String name;
    private String title;
    private String locationCity;
    private String locationAddress;
    private Integer distanceFromCity;
}
