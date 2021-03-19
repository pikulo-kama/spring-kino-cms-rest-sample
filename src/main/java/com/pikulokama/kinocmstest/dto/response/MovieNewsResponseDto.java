package com.pikulokama.kinocmstest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MovieNewsResponseDto {

    private Long id;

    private String title;

    private List<String> galleryImageUrls;

    private String description;

    private String mainImageUrl;

    private LocalDate publicationDate;

    private Boolean isVisible;

    private String videoUrl;

    private String seoUrl;

    private String seoTitle;

    private String seoKeywords;

    private String seoDescription;

}
