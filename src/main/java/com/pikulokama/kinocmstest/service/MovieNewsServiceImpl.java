package com.pikulokama.kinocmstest.service;

import com.pikulokama.kinocmstest.domain.MovieNews;
import com.pikulokama.kinocmstest.domain.User;
import com.pikulokama.kinocmstest.dto.form.MovieNewsFormDto;
import com.pikulokama.kinocmstest.dto.response.MovieNewsResponseDto;
import com.pikulokama.kinocmstest.exception.RestServiceException;
import com.pikulokama.kinocmstest.repository.MovieNewsRepository;
import com.pikulokama.kinocmstest.mapper.MovieNewsMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.pikulokama.kinocmstest.mapper.GalleryImageMapper.join;

@Log4j2
@Service
public class MovieNewsServiceImpl implements MovieNewsService {

    private final MovieNewsRepository movieNewsRepository;

    private final MovieNewsMapper movieNewsMapper;

    @Autowired
    public MovieNewsServiceImpl(MovieNewsRepository movieNewsRepository,
                                MovieNewsMapper movieNewsMapper) {

        this.movieNewsRepository = movieNewsRepository;
        this.movieNewsMapper = movieNewsMapper;
    }

    @Override
    public List<MovieNewsResponseDto> findAllFor(User user) {

        List<MovieNews> movieNews = movieNewsRepository.findAll();

        if (user.getRole().equals(User.UserRole.ROLE_ADMIN.name())) {

            return movieNews.stream()
                    .map(movieNewsMapper::mapToAdminMovieNewsResponse)
                    .collect(Collectors.toList());

        } else {

            return movieNews.stream()
                    .map(movieNewsMapper::mapToPublicMovieNewsResponse)
                    .collect(Collectors.toList());
        }

    }

    @Override
    public MovieNews findById(Long id) {
        return movieNewsRepository.findById(id)
                .orElseThrow(() -> new RestServiceException("Новости не найдени"));
    }

    @Override
    public MovieNewsResponseDto getMovieNewsResponseForCard(Long movieNewsId) {
        MovieNews movieNews = findById(movieNewsId);
        return movieNewsMapper.mapToMovieNewsResponseDtoForCard(movieNews);
    }

    @Override
    public void create(MovieNewsFormDto movieNewsFormDto) {

        MovieNews movieNews = MovieNews.builder()
                .title(movieNewsFormDto.getTitle())
                .description(movieNewsFormDto.getDescription())
                .mainImageUrl(movieNewsFormDto.getMainImageUrl())
                .galleryImageUrls(join(movieNewsFormDto.getGalleryImageUrls()))
                .videoUrl(movieNewsFormDto.getVideoUrl())
                .isVisible(movieNewsFormDto.getIsVisible())
                .publicationDate(movieNewsFormDto.getPublicationDate())
                .videoUrl(movieNewsFormDto.getVideoUrl())
                .seoBlock(movieNewsFormDto.getSeoBlock())
                .build();

        movieNewsRepository.save(movieNews);

        log.info("Movie news successfully created");
    }

    @Override
    public void update(MovieNewsFormDto movieNewsFormDto, Long movieNewsId) {
        MovieNews movieNews = findById(movieNewsId);

        MovieNews updatedMovieNews = movieNews.toBuilder()
                .title(movieNewsFormDto.getTitle())
                .description(movieNewsFormDto.getDescription())
                .mainImageUrl(movieNewsFormDto.getMainImageUrl())
                .galleryImageUrls(join(movieNewsFormDto.getGalleryImageUrls()))
                .videoUrl(movieNewsFormDto.getVideoUrl())
                .isVisible(movieNewsFormDto.getIsVisible())
                .publicationDate(movieNewsFormDto.getPublicationDate())
                .videoUrl(movieNewsFormDto.getVideoUrl())
                .seoBlock(movieNewsFormDto.getSeoBlock())
                .build();

        movieNewsRepository.save(updatedMovieNews);

        log.info("Movie news with id={} successfully updated", movieNewsId);
    }
}
