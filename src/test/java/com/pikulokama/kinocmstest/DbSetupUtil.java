package com.pikulokama.kinocmstest;

import com.pikulokama.kinocmstest.domain.Movie;
import com.pikulokama.kinocmstest.domain.MovieNews;
import com.pikulokama.kinocmstest.domain.SeoBlock;
import com.pikulokama.kinocmstest.domain.User;
import com.pikulokama.kinocmstest.repository.MovieNewsRepository;
import com.pikulokama.kinocmstest.repository.MovieRepository;
import com.pikulokama.kinocmstest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@SpringBootTest
class DbSetupUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieNewsRepository movieNewsRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void setup() {

        User admin = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder.encode("admin"))
                .role(User.UserRole.ROLE_ADMIN)
                .build();

        User commonUser = User.builder()
                .username("user")
                .password(bCryptPasswordEncoder.encode("user"))
                .role(User.UserRole.ROLE_USER)
                .build();

        userRepository.save(admin);
        userRepository.save(commonUser);

        SeoBlock seoBlock = SeoBlock.builder()
                .seoUrl("seoUrl")
                .seoDescription("seoDescription")
                .seoTitle("seoTitle")
                .seoKeywords("seoKeywords")
                .build();

        MovieNews movieNews = MovieNews.builder()
                .title("title")
                .isVisible(Boolean.TRUE)
                .mainImageUrl("mainImageUrl")
                .publicationDate(LocalDate.now())
                .description("description")
                .videoUrl("videoUrl")
                .galleryImageUrls("image1 image2 image3 image4")
                .seoBlock(seoBlock)
                .build();

        movieNewsRepository.save(movieNews);

        Movie movie = Movie.builder()
                .title("title")
                .description("description")
                .mainImageUrl("mainImageUrl")
                .galleryImageUrls("image1 image2 image3 image4")
                .videoUrl("videoUrl")
                .availableInTwoD(Boolean.FALSE)
                .availableInThreeD(Boolean.TRUE)
                .availableInImax(Boolean.TRUE)
                .releaseDate(LocalDate.now())
                .seoBlock(seoBlock)
                .build();

        movieRepository.save(movie);
    }

}
