package ru.example.spring.hotel.booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.example.spring.hotel.booking.model.User;
import ru.example.spring.hotel.booking.web.model.request.UserRequest;
import ru.example.spring.hotel.booking.web.model.response.UserResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    User toModel(UserRequest request);

    User toModel(Long id, UserRequest request);

    UserResponse toResponse(User model);

    List<UserResponse> toResponseList(List<User> models);

    default User toUpdatedModel(User user, UserRequest request) {
        user.setUsername(request.getUsername());
        return user;
    }
}
