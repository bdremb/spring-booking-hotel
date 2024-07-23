package ru.example.spring.hotel.booking.web.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.spring.hotel.booking.service.RoomService;
import ru.example.spring.hotel.booking.web.model.request.RoomRequestDto;
import ru.example.spring.hotel.booking.web.model.response.RoomResponseDto;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
    public ResponseEntity<RoomResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @PostMapping("/hotels/{hotelId}")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
    public ResponseEntity<RoomResponseDto> create(@RequestBody @Valid RoomRequestDto request, @PathVariable Long hotelId) {
        return ResponseEntity.status(CREATED).body(roomService.create(request, hotelId));
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
    public ResponseEntity<RoomResponseDto> update(@PathVariable("id") Long id, @RequestBody @Valid RoomRequestDto request) {
        return ResponseEntity.ok(roomService.update(id, request));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
