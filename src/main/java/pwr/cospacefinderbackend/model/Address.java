package pwr.cospacefinderbackend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the address.", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Country.", example = "country1")
    private String country;

    @Column(nullable = false)
    @Schema(description = "City.", example = "city1")
    private String city;

    @Column(nullable = false)
    @Schema(description = "Street and house number.", example = "ul. Xxx 1")
    private String address;

    @Column(nullable = false)
    @Schema(description = "Latitude.", example = "51.107883")
    private Double latitude;

    @Column(nullable = false)
    @Schema(description = "Longitude.", example = "17.038538")
    private Double longitude;
}
