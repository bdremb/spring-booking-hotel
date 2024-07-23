package ru.example.spring.hotel.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.example.spring.hotel.booking.exception.EntityNotFoundException;
import ru.example.spring.hotel.booking.mapper.HotelMapper;
import ru.example.spring.hotel.booking.model.Hotel;
import ru.example.spring.hotel.booking.repository.HotelRepository;
import ru.example.spring.hotel.booking.web.model.request.HotelRequestDto;
import ru.example.spring.hotel.booking.web.model.response.HotelResponseDto;
import ru.example.spring.hotel.booking.web.model.response.PageDto;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper mapper;

    public PageDto<HotelResponseDto> findAll(Integer offset, Integer limit) {
        final Page<Hotel> hotelPage = hotelRepository.findAll(PageRequest.of(offset, limit));
        return PageDto.<HotelResponseDto>builder()
                .items(mapper.toListDto(hotelPage))
                .count(hotelPage.getTotalElements())
                .build();
    }

    public HotelResponseDto findById(Long id) {
        return mapper.toDto(getHotelById(id));
    }

    public HotelResponseDto create(HotelRequestDto dto) {
        final Hotel createdHotel = hotelRepository.save(mapper.toModel(dto));
        return mapper.toDto(createdHotel);
    }

    public HotelResponseDto update(Long id, HotelRequestDto dto) {
        final Hotel toUpdateHotel = mapper.toUpdateModel(getHotelById(id), dto);
        return mapper.toDto(toUpdateHotel);
    }

    public void deleteById(Long id) {
        hotelRepository.delete(getHotelById(id));
    }

    protected Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Hotel.class));
    }

}
