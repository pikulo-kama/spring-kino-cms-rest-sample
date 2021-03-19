package com.pikulokama.kinocmstest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeoBlock {

    private String seoUrl;

    private String seoTitle;

    private String seoKeywords;

    private String seoDescription;

}
