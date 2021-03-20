package com.pikulokama.kinocmstest.service;

import com.pikulokama.kinocmstest.domain.Movie;
import com.pikulokama.kinocmstest.dto.form.MovieFormDto;
import com.pikulokama.kinocmstest.dto.response.MovieCollectionResponseDto;
import com.pikulokama.kinocmstest.dto.response.MovieResponseDto;
import com.pikulokama.kinocmstest.exception.RestServiceException;
import com.pikulokama.kinocmstest.mapper.GalleryImageMapper;
import com.pikulokama.kinocmstest.mapper.MovieMapper;
import com.pikulokama.kinocmstest.repository.MovieRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    private final GalleryImageMapper galleryImageMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,
                            MovieMapper movieMapper,
                            GalleryImageMapper galleryImageMapper) {

        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.galleryImageMapper = galleryImageMapper;
    }


    @Override
    public MovieCollectionResponseDto findAllSorted() {
        List<Movie> movies = movieRepository.findAll();
        return movieMapper.mapToMovieCollectionResponseDto(movies);
    }

    @Override
    public List<MovieResponseDto> findAllAndMapToResponseDto() {
        List<Movie> movies = movieRepository.findAll();
        return movieMapper.mapToMovieResponseDto(movies);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RestServiceException("Фильм не найден"));
    }

    @Override
    public void updateById(MovieFormDto movieFormDto, Long movieId) {

        Movie movie = findById(movieId);

        Movie updatedMovie = movie.toBuilder()
                .title(movieFormDto.getTitle())
                .description(movieFormDto.getDescription())
                .mainImageUrl(movieFormDto.getMainImageUrl())
                .galleryImageUrls(galleryImageMapper.join(movieFormDto.getGalleryImageUrls()))
                .videoUrl(movieFormDto.getVideoUrl())
                .availableInTwoD(movieFormDto.getAvailableInTwoD())
                .availableInThreeD(movieFormDto.getAvailableInThreeD())
                .availableInImax(movieFormDto.getAvailableInImax())
                .releaseDate(movieFormDto.getReleaseDate())
                .seoBlock(movieFormDto.getSeoBlock())
                .build();

        movieRepository.save(updatedMovie);

        log.info("Movie with id {} successfully created", movieId);
    }

    @Override
    public void create(MovieFormDto movieFormDto) {

        Movie movie = Movie.builder()
                .title(movieFormDto.getTitle())
                .description(movieFormDto.getDescription())
                .mainImageUrl(movieFormDto.getMainImageUrl())
                .galleryImageUrls(galleryImageMapper.join(movieFormDto.getGalleryImageUrls()))
                .videoUrl(movieFormDto.getVideoUrl())
                .availableInTwoD(movieFormDto.getAvailableInTwoD())
                .availableInThreeD(movieFormDto.getAvailableInThreeD())
                .availableInImax(movieFormDto.getAvailableInImax())
                .releaseDate(movieFormDto.getReleaseDate())
                .seoBlock(movieFormDto.getSeoBlock())
                .build();

        movieRepository.save(movie);

        log.info("Movie successfully created");
    }

    @Override
    public MovieResponseDto getCardMovieResponseDto(Long movieId) {
        Movie movie = findById(movieId);
        return movieMapper.mapToCardMovieResponseDto(movie);
    }

}
