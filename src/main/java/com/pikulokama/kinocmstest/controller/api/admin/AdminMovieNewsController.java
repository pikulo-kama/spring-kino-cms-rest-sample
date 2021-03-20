package com.pikulokama.kinocmstest.controller.api.admin;

import com.pikulokama.kinocmstest.domain.User;
import com.pikulokama.kinocmstest.dto.form.MovieNewsFormDto;
import com.pikulokama.kinocmstest.dto.response.MovieNewsResponseDto;
import com.pikulokama.kinocmstest.service.MovieNewsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/admin/news")
public class AdminMovieNewsController {

    private final MovieNewsService movieNewsService;

    public AdminMovieNewsController(MovieNewsService movieNewsService) {
        this.movieNewsService = movieNewsService;
    }

    @GetMapping
    public List<MovieNewsResponseDto> findAll(@AuthenticationPrincipal User user) {
        return movieNewsService.findAllFor(user);
    }

    @GetMapping("/{id}")
    public MovieNewsResponseDto findById(@PathVariable("id") Long id) {
        return movieNewsService.getMovieNewsResponseForCard(id);
    }

    @PostMapping("/create")
    public void create(@Validated MovieNewsFormDto movieNewsFormDto) {
        movieNewsService.create(movieNewsFormDto);
    }

    @PostMapping("/update/{id}")
    public void updateById(@Validated MovieNewsFormDto movieNewsFormDto,
                           @PathVariable("id") Long movieNewsId) {

        movieNewsService.updateById(movieNewsFormDto, movieNewsId);
    }

}
