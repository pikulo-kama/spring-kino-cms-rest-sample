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
public class MovieNewsFormDto {

    @NotBlank(message = "Название фильма отсутствует")
    private String title;

    @NotNull(message = "Статус видимости новостей отсутствует")
    private Boolean isVisible;

    private String mainImageUrl;

    private List<String> galleryImageUrls;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    @NotBlank(message = "Описание новостей отсутствует")
    @Pattern(regexp = ".{50,500}", message = "Описание должно бить от 50 до 500 символов")
    private String description;

    private String videoUrl;

    @Embedded.Nullable
    private SeoBlock seoBlock;

}
