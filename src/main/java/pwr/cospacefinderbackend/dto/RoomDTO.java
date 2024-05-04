package pwr.cospacefinderbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    @Schema(description = "Id of the space that room belongs to.", example = "1")
    private Long spaceId;

    @Schema(description = "Name of the room.", example = "room1")
    private String name;

    @Schema(description = "Room's type id.")
    private Long roomTypeId;

    @Schema(description = "Room's capacity.", example = "10")
    private int capacity;

    @Schema(description = "Room's floor.", example = "1")
    private Integer floor;

    @Schema(description = "Price for renting the desk in the room.", example = "100.0")
    private Double price;
}
