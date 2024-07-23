package ru.example.spring.hotel.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.example.spring.hotel.booking.exception.EntityNotFoundException;
import ru.example.spring.hotel.booking.mapper.HotelMapper;
import ru.example.spring.hotel.booking.model.Hotel;
import ru.example.spring.hotel.booking.repository.HotelRepository;
import ru.example.spring.hotel.booking.web.model.request.HotelRequestDto;
import ru.example.spring.hotel.booking.web.model.response.HotelResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper mapper;

    public List<HotelResponseDto> getAll(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toListDto(hotelRepository.findAll(pageable));
    }

    private HotelResponseDto getById(Long id) {
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

    public void delete(Long id) {
        hotelRepository.delete(getHotelById(id));
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Hotel.class));
    }

}
