package com.pikulokama.kinocmstest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieCollectionResponseDto {

    private List<MovieResponseDto> ongoingMovies;

    private List<MovieResponseDto> comingSoonMovies;

}
