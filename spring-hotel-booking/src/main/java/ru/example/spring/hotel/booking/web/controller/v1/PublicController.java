package ru.example.spring.hotel.booking.web.controller.v1;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.spring.hotel.booking.model.RoleType;
import ru.example.spring.hotel.booking.service.UserService;
import ru.example.spring.hotel.booking.web.model.request.UserRequest;
import ru.example.spring.hotel.booking.web.model.response.UserResponse;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class PublicController {

    private final UserService userService;

    @PostMapping("/account")
    public ResponseEntity<UserResponse> createUserAccount(
            @RequestBody @Valid UserRequest request,
            @RequestParam RoleType roleType
    ) {
        return ResponseEntity.status(CREATED).body(userService.create(request, roleType));
    }

}
