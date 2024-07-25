package ru.example.spring.hotel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.spring.hotel.booking.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}