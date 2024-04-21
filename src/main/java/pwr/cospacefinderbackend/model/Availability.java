package pwr.cospacefinderbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "availability")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the availability.", example = "1")
    private long Id;

    @ManyToOne
    @JsonIgnore
    @Schema(description = "Space that availability belongs to.", example = "1")
    private Space space;

    @Column(nullable = false)
    @Schema(description = "Day of the week.", example = "1")
    private Integer dayOfWeek;

    @Column(nullable = false)
    @Schema(description = "Start time of the availability.", example = "08:00")
    private String startTime;

    @Column(nullable = false)
    @Schema(description = "End time of the availability.", example = "16:00")
    private String endTime;
}
