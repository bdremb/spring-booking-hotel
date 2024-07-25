package ru.example.spring.hotel.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.spring.hotel.booking.exception.EntityNotFoundException;
import ru.example.spring.hotel.booking.exception.NameNotUniqueException;
import ru.example.spring.hotel.booking.mapper.UserMapper;
import ru.example.spring.hotel.booking.model.Role;
import ru.example.spring.hotel.booking.model.RoleType;
import ru.example.spring.hotel.booking.model.User;
import ru.example.spring.hotel.booking.repository.UserRepository;
import ru.example.spring.hotel.booking.web.model.request.UserRequest;
import ru.example.spring.hotel.booking.web.model.response.UserResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    public List<UserResponse> findAll() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    public UserResponse findById(Long id) {
        return userMapper.toResponse(getUserById(id));
    }

    public UserResponse create(UserRequest request, RoleType roleType) {
        if (isUserNameAlreadyExists(request.getUsername())) {
            throw new NameNotUniqueException("User name already exists");
        }
        Role role = Role.from(roleType);
        roleService.addRole(role);

        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        return userMapper.toResponse(userRepository.saveAndFlush(user));
    }

    public UserResponse update(Long userId, UserRequest request) {
        User updatedUser = userMapper.toUpdatedModel(getUserById(userId), request);
        return userMapper.toResponse(userRepository.save(updatedUser));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUserByName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username not found"));
    }

    private boolean isUserNameAlreadyExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    protected User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, User.class));
    }

}
