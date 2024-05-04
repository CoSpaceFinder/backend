package pwr.cospacefinderbackend.model;

import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the room.", example = "1")
    private Long id;

    @ManyToOne
    @Schema(description = "Space that room belongs to.", example = "1")
    private Space space;

    @Column(nullable = false)
    @Schema(description = "Name of the room.", example = "room1")
    private String name;

    @ManyToOne
    @Schema(description = "Room's type.")
    private RoomType roomType;

    @Column(nullable = false)
    @Schema(description = "Room's capacity.", example = "10")
    private Integer capacity;

    @Column(nullable = false)
    @Schema(description = "Room's floor.", example = "1")
    private Integer floor;

    @Column(nullable = false)
    @Schema(description = "Price for renting the desk in the room.", example = "100.0")
    private Double price;
}
