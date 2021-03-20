package com.pikulokama.kinocmstest.mapper;

import com.pikulokama.kinocmstest.domain.MovieNews;
import com.pikulokama.kinocmstest.dto.response.MovieNewsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieNewsMapper {

    private final GalleryImageMapper galleryImageMapper;

    @Autowired
    public MovieNewsMapper(GalleryImageMapper galleryImageMapper) {
        this.galleryImageMapper = galleryImageMapper;
    }

    public MovieNewsResponseDto mapToPublicMovieNewsResponse(MovieNews movieNews) {
        return MovieNewsResponseDto.builder()
                .id(movieNews.getId())
                .title(movieNews.getTitle())
                .description(movieNews.getDescription())
                .mainImageUrl(movieNews.getMainImageUrl())
                .publicationDate(movieNews.getPublicationDate())
                .isVisible(movieNews.getIsVisible())
                .build();
    }

    public MovieNewsResponseDto mapToAdminMovieNewsResponse(MovieNews movieNews) {
        return MovieNewsResponseDto.builder()
                .id(movieNews.getId())
                .title(movieNews.getTitle())
                .publicationDate(movieNews.getPublicationDate())
                .isVisible(movieNews.getIsVisible())
                .build();
    }

    public MovieNewsResponseDto mapToMovieNewsResponseDtoForCard(MovieNews movieNews) {
        return MovieNewsResponseDto.builder()
                .id(movieNews.getId())
                .title(movieNews.getTitle())
                .isVisible(movieNews.getIsVisible())
                .mainImageUrl(movieNews.getMainImageUrl())
                .galleryImageUrls(galleryImageMapper.split(movieNews.getGalleryImageUrls()))
                .publicationDate(movieNews.getPublicationDate())
                .description(movieNews.getDescription())
                .videoUrl(movieNews.getVideoUrl())
                .seoUrl(movieNews.getSeoBlock().getSeoUrl())
                .seoTitle(movieNews.getSeoBlock().getSeoTitle())
                .seoDescription(movieNews.getSeoBlock().getSeoDescription())
                .seoKeywords(movieNews.getSeoBlock().getSeoKeywords())
                .build();
    }


}
