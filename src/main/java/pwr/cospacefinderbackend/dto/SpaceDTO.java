package pwr.cospacefinderbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pwr.cospacefinderbackend.model.Convenience;
import pwr.cospacefinderbackend.model.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceDTO {
    @Schema(description = "Name of the space.", example = "space1")
    private String name;

    @Schema(description = "Description of the space.", example = "description1")
    private String description;

    @Schema(description = "Address of the space.")
    private AddressDTO address;

    @Schema(description = "Space's capacity.", example = "100")
    private Integer capacity;

    @Schema(description = "User's grades average.", example = "4.5")
    private Double grade;

    @Schema(description = "Availability of the space.")
    private List<AvailabilityDTO> availability;

    @Schema(description = "Conveniences of the space.")
    private List<Convenience> conveniences;

    @Schema(description = "Id of the owner of the space.", example = "1")
    private Long ownerId;
}
