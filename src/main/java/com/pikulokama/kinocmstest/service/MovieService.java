package com.pikulokama.kinocmstest.service;

import com.pikulokama.kinocmstest.domain.Movie;
import com.pikulokama.kinocmstest.dto.form.MovieFormDto;
import com.pikulokama.kinocmstest.dto.response.MovieCollectionResponseDto;
import com.pikulokama.kinocmstest.dto.response.MovieResponseDto;

import java.util.List;

public interface MovieService {

    MovieCollectionResponseDto findAllSorted();

    List<MovieResponseDto> findAllAndMapToResponseDto();

    Movie findById(Long id);

    void update(MovieFormDto movieFormDto, Long movieId);

    void create(MovieFormDto movieFormDto);

    MovieResponseDto getMovieResponseDtoForCard(Long movieId);

}
