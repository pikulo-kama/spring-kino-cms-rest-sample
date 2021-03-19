package com.pikulokama.kinocmstest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDate;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    private Long id;

    private String title;

    private String description;

    private String mainImageUrl;

    private String galleryImageUrls;

    private String videoUrl;

    private Boolean availableInTwoD;

    private Boolean availableInThreeD;

    private Boolean availableInImax;

    private LocalDate releaseDate;

    @Embedded.Nullable
    private SeoBlock seoBlock;

}
