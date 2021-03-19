package com.pikulokama.kinocmstest.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieFormDto {

    @NotBlank(message = "Название фильма необходимое")
    private String title;

    @NotBlank(message = "Описание необходимое")
    @Pattern(regexp = ".{50,500}", message = "Описание должно бить от 50 до 500 символов")
    private String description;

    private String mainImageUrl;

    private List<String> galleryImageUrls;

    private String videoUrl;

    @NotNull(message = "Значение '2D сеанси' необходимое")
    private Boolean availableInTwoD;

    @NotNull(message = "Значение '3D сеанси' необходимое")
    private Boolean availableInThreeD;

    @NotNull(message = "Значение 'IMAX сеанси' необходимое")
    private Boolean availableInImax;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private String seoUrl;

    private String seoTitle;

    private String seoKeywords;

    private String seoDescription;

}
