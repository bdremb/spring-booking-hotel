package ru.example.spring.hotel.booking.web.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PageDto<T> {
    private List<T> items;
    private Long count;
}
