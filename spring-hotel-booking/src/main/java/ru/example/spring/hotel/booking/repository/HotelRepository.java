package ru.example.spring.hotel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.spring.hotel.booking.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
