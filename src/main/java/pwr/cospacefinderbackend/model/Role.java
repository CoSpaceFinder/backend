package pwr.cospacefinderbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the role.", example = "1")
    private long Id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Name of the role.", example = "client")
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> users;
}
