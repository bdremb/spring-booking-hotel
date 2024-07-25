package ru.example.spring.hotel.booking.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.spring.hotel.booking.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @EntityGraph(attributePaths = {"role"})
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByUserIdAndUsername(Long id, String username);

    @EntityGraph(attributePaths = {"role"})
    List<User> findAll();

}
