package ru.example.spring.hotel.booking.web.model.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RoomRequestDto {
    private String name;
    private String description;
    private Integer number;
    private Double price;
    private Integer maxVisitorsCount;

    private LocalDate bookingStartDate;
    private LocalDate bookingEndDate;
}
