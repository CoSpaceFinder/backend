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

    @Column(nullable = false, unique = true)
    @Schema(description = "Name of the space.", example = "space1")
    private String name;

    @Column(nullable = false)
    @Schema(description = "Description of the space.", example = "description1")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Address of the space.")
    private Address address;

    @Column(nullable = false)
    @Schema(description = "Space's capacity.", example = "100")
    private Integer capacity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Space's availabilities.")
    private List<Availability> availabilities;

    @ManyToMany(fetch = FetchType.EAGER)
    @Schema(description = "Conveniences of the space.")
    private List<Convenience> conveniences;

    @ManyToOne
    @Schema(description = "Owner of the space.")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Images of the space.")
    private List<Image> images;
}
