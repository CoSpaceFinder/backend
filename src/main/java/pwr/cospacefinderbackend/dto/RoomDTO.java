package pwr.cospacefinderbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long buildingId;

    private String name;

    private String type;

    private Integer capacity;
}
