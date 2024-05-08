package pwr.cospacefinderbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Schema(description = "User's name.", example = "John")
    private String name;

    @Schema(description = "User's surname.", example = "Doe")
    private String surname;

    @Schema(description = "User's email address.", example = "john.doe@gmail.com")
    private String mail;

    @Schema(description = "User's role id.", example = "1")
    private Long roleId;
}
