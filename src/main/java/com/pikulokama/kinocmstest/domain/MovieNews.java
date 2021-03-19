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
public class MovieNews {

    @Id
    private Long id;

    private String title;

    private Boolean isVisible;

    private String mainImageUrl;

    private String galleryImageUrls;

    private LocalDate publicationDate;

    private String description;

    private String videoUrl;

    @Embedded.Nullable
    private SeoBlock seoBlock;

}
