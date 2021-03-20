package com.pikulokama.kinocmstest.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class GalleryImageMapper {

    @Value("${movies.image-gallery.mapper.delimiter}")
    private  String delimiter;

    @Value("${movies.image-gallery.mapper.default-image}")
    private String defaultImage;

    public List<String> split(String galleryImageUrls) {
        return Arrays.asList(galleryImageUrls.split(delimiter));
    }

    public String join(List<String> galleryImageUrls) {

        if (isNull(galleryImageUrls)) {
            return defaultImage;
        }

        return String.join(delimiter, galleryImageUrls);
    }
}
