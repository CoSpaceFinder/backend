package pwr.cospacefinderbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDTO {
    @Schema(description = "Name of the room type.", example = "open space")
    private String name;
}
