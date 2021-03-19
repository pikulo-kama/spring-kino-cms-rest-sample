package com.pikulokama.kinocmstest.repository;

import com.pikulokama.kinocmstest.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findAll();

}
