package com.pikulokama.kinocmstest.mapper;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

public class GalleryImageMapper {

    // It could be in .yaml file
    public static final String DELIMITER = "\\s";
    public static final String DEFAULT_IMAGE = "default.jpg";

    public static List<String> split(String galleryImageUrls) {
        return Arrays.asList(galleryImageUrls.split(DELIMITER));
    }

    public static String join(List<String> galleryImageUrls) {

        if (isNull(galleryImageUrls)) {
            return DEFAULT_IMAGE;
        }

        return String.join(DELIMITER, galleryImageUrls);
    }
}
