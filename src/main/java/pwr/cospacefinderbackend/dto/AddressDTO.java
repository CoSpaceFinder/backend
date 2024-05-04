package pwr.cospacefinderbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @Schema(description = "Country.", example = "country1")
    private String country;

    @Schema(description = "City.", example = "city1")
    private String city;

    @Schema(description = "Street and house number.", example = "ul. Xxx 1")
    private String address;

    @Schema(description = "Latitude.", example = "51.107883")
    private Double latitude;

    @Schema(description = "Longitude.", example = "17.038538")
    private Double longitude;
}
