package pwr.cospacefinderbackend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "space")
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the space.", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Name of the space.", example = "space1")
    private String name;

    @Column(nullable = false)
    @Schema(description = "Description of the space.", example = "description1")
    private String description;

    @OneToOne
    @Schema(description = "Address of the space.", example = "address1")
    private Address address;

    @Column(nullable = false)
    @Schema(description = "Space's capacity.", example = "100")
    private Integer capacity;

    @Column(nullable = false)
    @Schema(description = "User's grades average.", example = "4.5")
    private Double grade;

    @OneToMany(mappedBy = "space")
    @Schema(description = "Availability of the space.")
    private List<Availability> availability;

    @ManyToMany
    @Schema(description = "Conveniences of the space.")
    private List<Convenience> conveniences;
}
