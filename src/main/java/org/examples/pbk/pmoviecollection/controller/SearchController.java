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
 * Created by pbkvi on 26.07.2017.
 */
@RestController
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/search")
    public DiscoverMoviesJsonResponse discoverMovies(
            @RequestParam(value="language", required = false, defaultValue = "ru-RU") String language,
            @RequestParam(value = "query") String query,
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "year", required = false) String year) {

        String url = String.format("https://api.themoviedb.org/3/search/movie?api_key=%s&language=%s&query=%s&page=%s&year=%s", AppConfig.API_KEY, language, query, page, year);

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
