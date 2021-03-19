package com.pikulokama.kinocmstest.repository;

import com.pikulokama.kinocmstest.domain.MovieNews;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieNewsRepository extends CrudRepository<MovieNews, Long> {

    List<MovieNews> findAll();

}
