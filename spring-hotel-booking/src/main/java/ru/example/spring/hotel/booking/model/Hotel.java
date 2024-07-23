package ru.example.spring.hotel.booking.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "hotel")
@FieldNameConstants
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelId;

    private String name;
    private String title;
    private String locationCity;
    private String locationAddress;
    private Integer distanceFromCity;
    private Integer rating;
    private Integer ratingsCount;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<Room> rooms = new ArrayList<>();
}
