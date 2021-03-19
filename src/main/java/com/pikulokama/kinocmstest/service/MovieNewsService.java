package com.pikulokama.kinocmstest.service;

import com.pikulokama.kinocmstest.domain.MovieNews;
import com.pikulokama.kinocmstest.domain.User;
import com.pikulokama.kinocmstest.dto.form.MovieNewsFormDto;
import com.pikulokama.kinocmstest.dto.response.MovieNewsResponseDto;

import java.util.List;

public interface MovieNewsService {

    List<MovieNewsResponseDto> findAllFor(User user);

    MovieNews findById(Long id);

    MovieNewsResponseDto getMovieNewsResponseForCard(Long movieNewsId);

    void create(MovieNewsFormDto movieNewsFormDto);

    void update(MovieNewsFormDto movieNewsFormDto, Long movieNewsId);

}
