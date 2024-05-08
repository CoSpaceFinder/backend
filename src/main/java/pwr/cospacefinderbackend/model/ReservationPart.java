package pwr.cospacefinderbackend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation_part", uniqueConstraints = @UniqueConstraint(name = "UniqueReservationPart",
        columnNames = {"room_id", "desk", "date"}))
public class ReservationPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the reservation.", example = "1")
    private Long id;

    @ManyToOne
    @Schema(description = "Room that reservation is made for.", example = "1")
    private Room room;

    @Column(nullable = false)
    @Schema(description = "Desk that reservation is made for.", example = "1")
    private Integer desk;

    @Column(nullable = false)
    @Schema(description = "Date of the reservation.", example = "2024-06-01")
    private LocalDate date;

    @ManyToOne
    @Schema(description = "Reservation that reservation part is part of.", example = "1")
    private Reservation reservation;
}
