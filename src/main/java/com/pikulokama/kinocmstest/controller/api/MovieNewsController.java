package com.pikulokama.kinocmstest.controller.api;

import com.pikulokama.kinocmstest.domain.MovieNews;
import com.pikulokama.kinocmstest.domain.User;
import com.pikulokama.kinocmstest.dto.response.MovieNewsResponseDto;
import com.pikulokama.kinocmstest.service.MovieNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/news")
public class MovieNewsController {

    private final MovieNewsService movieNewsService;

    @Autowired
    public MovieNewsController(MovieNewsService movieNewsService) {
        this.movieNewsService = movieNewsService;
    }

    @GetMapping
    public List<MovieNewsResponseDto> findAll(@AuthenticationPrincipal User loggedInUser) {
        return movieNewsService.findAllFor(loggedInUser);
    }

    @GetMapping("/{id}")
    public MovieNews findById(@PathVariable("id") Long id) {
        return movieNewsService.findById(id);
    }

}
