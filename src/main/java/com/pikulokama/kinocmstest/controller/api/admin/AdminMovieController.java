package com.pikulokama.kinocmstest.controller.api.admin;

import com.pikulokama.kinocmstest.dto.form.MovieFormDto;
import com.pikulokama.kinocmstest.dto.response.MovieCollectionResponseDto;
import com.pikulokama.kinocmstest.service.MovieService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/movies")
public class AdminMovieController {

    private final MovieService movieService;

    public AdminMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public MovieCollectionResponseDto findAll() {
        return movieService.findAllSorted();
    }

    @PostMapping("/create")
    public void create(@Validated MovieFormDto movieFormDto) {
        movieService.create(movieFormDto);
    }

    @PostMapping("/update/{id}")
    public void updateById(@PathVariable("id") Long movieId, @Validated MovieFormDto movieFormDto) {
        movieService.updateById(movieFormDto, movieId);
    }

}
