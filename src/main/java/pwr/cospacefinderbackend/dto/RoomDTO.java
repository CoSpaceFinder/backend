package pwr.cospacefinderbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pwr.cospacefinderbackend.model.RoomType;
import pwr.cospacefinderbackend.model.Space;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Space space;

    private String name;

    private String type;

    private RoomType roomType;

    private int capacity;
}
