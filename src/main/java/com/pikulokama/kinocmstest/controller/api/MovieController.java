package com.pikulokama.kinocmstest.controller.api;

import com.pikulokama.kinocmstest.dto.response.MovieResponseDto;
import com.pikulokama.kinocmstest.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponseDto> findAll() {
        return movieService.findAllAndMapToResponseDto();
    }

    @GetMapping("/{id}")
    public MovieResponseDto findById(@PathVariable("id") Long id) {
        return movieService.getMovieResponseDtoForCard(id);
    }

}
