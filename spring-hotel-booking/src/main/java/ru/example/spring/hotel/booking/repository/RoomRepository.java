package ru.example.spring.hotel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.spring.hotel.booking.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}