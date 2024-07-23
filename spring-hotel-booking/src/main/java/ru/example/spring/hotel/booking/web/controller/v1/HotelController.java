package ru.example.spring.hotel.booking.web.controller.v1;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.spring.hotel.booking.service.HotelService;
import ru.example.spring.hotel.booking.web.model.request.HotelRequestDto;
import ru.example.spring.hotel.booking.web.model.response.HotelResponseDto;
import ru.example.spring.hotel.booking.web.model.response.PageDto;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
    public ResponseEntity<PageDto<HotelResponseDto>> findAll(
            @RequestParam @Min(0) @Max(Integer.MAX_VALUE) Integer offset,
            @RequestParam @Min(1) @Max(Integer.MAX_VALUE) Integer limit
    ) {
        return ResponseEntity.ok(hotelService.findAll(offset, limit));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
    public ResponseEntity<HotelResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.findById(id));
    }

    @PostMapping
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
    public ResponseEntity<HotelResponseDto> create(@RequestBody @Valid HotelRequestDto request) {
        return ResponseEntity.status(CREATED).body(hotelService.create(request));
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
    public ResponseEntity<HotelResponseDto> update(@PathVariable("id") Long id, @RequestBody @Valid HotelRequestDto request) {
        return ResponseEntity.ok(hotelService.update(id, request));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hotelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
