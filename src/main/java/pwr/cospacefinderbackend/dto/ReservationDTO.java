package pwr.cospacefinderbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    @Schema(description = "Id of the user that made the reservation.", example = "1")
    private Long userId;

    @Schema(description = "Id of the room that reservation is made for.", example = "1")
    private Long roomId;

    @Schema(description = "Desk that reservation is made for.", example = "1")
    private Integer desk;

    @Schema(description = "Date of the reservation.", example = "2024-06-01")
    private LocalDate date;
}
