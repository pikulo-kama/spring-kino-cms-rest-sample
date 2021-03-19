package com.pikulokama.kinocmstest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MovieResponseDto {

    private Long id;

    private String title;

    private String mainImageUrl;

    private List<String> galleryImageUrls;

    private Boolean availableInTwoD;

    private Boolean availableInThreeD;

    private Boolean availableInImax;

    private String status;

}
