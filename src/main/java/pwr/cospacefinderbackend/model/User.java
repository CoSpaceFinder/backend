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
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the user.", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "User's name.", example = "John")
    private String name;

    @Column(nullable = false)
    @Schema(description = "User's surname.", example = "Doe")
    private String surname;

    @Column(nullable = false, unique = true)
    @Schema(description = "User's email address.", example = "john.doe@gmail.com")
    private String mail;

    @ManyToOne
    @Schema(description = "User's role.")
    private Role role;
}
