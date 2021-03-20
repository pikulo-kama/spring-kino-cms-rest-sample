package com.pikulokama.kinocmstest.dto.form;

import com.pikulokama.kinocmstest.domain.SeoBlock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Embedded;
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

    @NotBlank(message = "Название фильма отсутствует")
    private String title;

    @NotBlank(message = "Описание фильма отсутствует")
    @Pattern(regexp = ".{50,500}", message = "Описание должно бить от 50 до 500 символов")
    private String description;

    private String mainImageUrl;

    private List<String> galleryImageUrls;

    private String videoUrl;

    @NotNull(message = "Значение 'Доступен в 2D' отсутствует")
    private Boolean availableInTwoD;

    @NotNull(message = "Значение 'Доступен в 3D' отсутствует")
    private Boolean availableInThreeD;

    @NotNull(message = "Значение 'Доступен в IMAX' отсутствует")
    private Boolean availableInImax;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Embedded.Nullable
    private SeoBlock seoBlock;

}
