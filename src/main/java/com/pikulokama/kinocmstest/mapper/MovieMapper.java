package com.pikulokama.kinocmstest.mapper;

import com.pikulokama.kinocmstest.domain.Movie;
import com.pikulokama.kinocmstest.dto.response.MovieCollectionResponseDto;
import com.pikulokama.kinocmstest.dto.response.MovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    private final GalleryImageMapper galleryImageMapper;

    @Autowired
    public MovieMapper(GalleryImageMapper galleryImageMapper) {
        this.galleryImageMapper = galleryImageMapper;
    }

    public MovieResponseDto mapToCardMovieResponseDto(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .mainImageUrl(movie.getMainImageUrl())
                .availableInTwoD(movie.getAvailableInTwoD())
                .availableInThreeD(movie.getAvailableInThreeD())
                .availableInImax(movie.getAvailableInImax())
                .galleryImageUrls(galleryImageMapper.split(movie.getGalleryImageUrls()))
                .build();
    }

    public List<MovieResponseDto> mapToMovieResponseDto(List<Movie> movies) {

        return movies.stream()
                .map(this::mapToMovieResponseDto)
                .collect(Collectors.toList());
    }

    public MovieResponseDto mapToMovieResponseDto(Movie movie) {

        String movieStatus;

        if (movie.getReleaseDate().isBefore(LocalDate.now())) {
            movieStatus = "Сейчас в кино";

        } else {

            movieStatus = "С " + movie.getReleaseDate()
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        return MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .availableInTwoD(movie.getAvailableInTwoD())
                .availableInThreeD(movie.getAvailableInThreeD())
                .availableInImax(movie.getAvailableInImax())
                .mainImageUrl(movie.getMainImageUrl())
                .status(movieStatus)
                .build();
    }

    public MovieCollectionResponseDto mapToMovieCollectionResponseDto(List<Movie> movies) {

        List<MovieResponseDto> ongoingMovies = new ArrayList<>();
        List<MovieResponseDto> comingSoonMovies = new ArrayList<>();

        movies.forEach(movie -> {

            if (movie.getReleaseDate().isBefore(LocalDate.now().plus(1, ChronoUnit.DAYS))) {
                ongoingMovies.add(mapToShortenedMovieResponseDto(movie));
            } else {
                comingSoonMovies.add(mapToShortenedMovieResponseDto(movie));
            }

        });

        return MovieCollectionResponseDto.builder()
                .comingSoonMovies(comingSoonMovies)
                .ongoingMovies(ongoingMovies)
                .build();
    }

    private MovieResponseDto mapToShortenedMovieResponseDto(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .mainImageUrl(movie.getMainImageUrl())
                .build();
    }
}
