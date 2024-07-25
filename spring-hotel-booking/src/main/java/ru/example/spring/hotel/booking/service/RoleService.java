package ru.example.spring.hotel.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.spring.hotel.booking.model.Role;
import ru.example.spring.hotel.booking.model.RoleType;
import ru.example.spring.hotel.booking.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void initAuthorities() {
        if (roleRepository.findAll().isEmpty()) {
            final List<Role> authorities = Arrays.stream(RoleType.values())
                    .map(role -> Role.builder()
                            .authority(role)
                            .build()).toList();
            roleRepository.saveAll(authorities);
        }
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }

}
