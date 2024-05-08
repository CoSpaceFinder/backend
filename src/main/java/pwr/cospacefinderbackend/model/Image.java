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
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the image.", example = "1")
    private long Id;

    @Column(nullable = false)
    @Schema(description = "Caption of the image.", example = "image1")
    private String caption;

    @Column(nullable = false)
    @Schema(description = "URL of the image.", example = "https://example.com/image1.jpg")
    private String url;
}
