package ru.example.spring.hotel.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.spring.hotel.booking.exception.EntityNotFoundException;
import ru.example.spring.hotel.booking.mapper.RoomMapper;
import ru.example.spring.hotel.booking.model.Room;
import ru.example.spring.hotel.booking.repository.RoomRepository;
import ru.example.spring.hotel.booking.web.model.request.RoomRequestDto;
import ru.example.spring.hotel.booking.web.model.response.RoomResponseDto;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper mapper;

    public RoomResponseDto findById(Long id) {
        return mapper.toDto(getHotelById(id));
    }

    public RoomResponseDto create(RoomRequestDto dto) {
        final Room createdRoom = roomRepository.save(mapper.toModel(dto));
        return mapper.toDto(createdRoom);
    }

    public RoomResponseDto update(Long id, RoomRequestDto dto) {
        final Room toUpdateRoom = mapper.toUpdateModel(getHotelById(id), dto);
        return mapper.toDto(toUpdateRoom);
    }

    public void deleteById(Long id) {
        roomRepository.delete(getHotelById(id));
    }

    private Room getHotelById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Room.class));
    }

}
