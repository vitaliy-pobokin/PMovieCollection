package org.examples.pbk.pmoviecollection.controller;

import org.examples.pbk.pmoviecollection.AppConfig;
import org.examples.pbk.pmoviecollection.DiscoverMoviesJsonResponse;
import org.examples.pbk.pmoviecollection.converter.themoviedb.DiscoverMoviesJsonResponseConverter;
import org.examples.pbk.pmoviecollection.themoviedb.DiscoverMovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Vitalik on 25.07.2017.
 */
@RestController
public class DiscoverController {
    @Autowired
    private RestTemplate restTemplate;

    /*@Autowired
    private MovieDao db;*/

    @RequestMapping("/discover")
    public DiscoverMoviesJsonResponse discoverMovies(
            @RequestParam(value="language", defaultValue = "ru-RU") String language,
            @RequestParam(value="sort_by", defaultValue = "popularity.desc") String sortBy,
            @RequestParam(value = "page", required = false, defaultValue = "1") String page) {

        String url = String.format("https://api.themoviedb.org/3/discover/movie?api_key=%s&language=%s&sort_by=%s&page=%s", AppConfig.API_KEY, language, sortBy, page);

        System.out.println(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<DiscoverMovieResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, DiscoverMovieResponse.class);

        DiscoverMovieResponse response = responseEntity.getBody();

        DiscoverMoviesJsonResponse result = DiscoverMoviesJsonResponseConverter.convert(response);

        return result;
    }
}
