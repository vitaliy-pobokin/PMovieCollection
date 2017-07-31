package org.examples.pbk.pmoviecollection.controller;

import org.examples.pbk.pmoviecollection.AppConfig;
import org.examples.pbk.pmoviecollection.model.Movie;
import org.examples.pbk.pmoviecollection.converter.themoviedb.MovieResponseFullToMovieConverter;
import org.examples.pbk.pmoviecollection.themoviedb.MovieResponseFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * Created by pbkvi on 26.07.2017.
 */@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
    public Movie showMovieInfo(@PathVariable String movieId,
                               @RequestParam(value="language", required = false, defaultValue = "ru-RU") String language) {

        // if there is no such movie in a database:
        String url = String.format("https://api.themoviedb.org/3/movie/%s?api_key=%s&language=%s", movieId, AppConfig.API_KEY, language);

        System.out.println(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<MovieResponseFull> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, MovieResponseFull.class);

        MovieResponseFull response = responseEntity.getBody();

        Movie result = MovieResponseFullToMovieConverter.convert(response);

        return result;
    }
}
