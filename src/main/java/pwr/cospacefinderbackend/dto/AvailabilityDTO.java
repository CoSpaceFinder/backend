package pwr.cospacefinderbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityDTO {
    @Schema(description = "Day of the week.", example = "1")
    private Integer dayOfWeek;

    @Schema(description = "Start time of the availability.", example = "08:00")
    private LocalTime startTime;

    @Schema(description = "End time of the availability.", example = "16:00")
    private LocalTime endTime;
}
