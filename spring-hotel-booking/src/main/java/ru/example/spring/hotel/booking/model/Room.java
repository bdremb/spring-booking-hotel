package ru.example.spring.hotel.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "room")
@FieldNameConstants
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    private String name;
    private String description;
    private Integer number;
    private Double price;
    private Integer maxVisitorsCount;

    private LocalDate bookingStartDate;
    private LocalDate bookingEndDate;

    @ManyToOne
    @JoinColumn(name="hotelId")
    @ToString.Exclude
    private Hotel hotel;
}
