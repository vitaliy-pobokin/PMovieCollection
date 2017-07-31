package org.examples.pbk.pmoviecollection;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

/**
 * Created by Vitalik on 25.07.2017.
 */
@Configuration
public class AppConfig {

    public static final String API_KEY = "d52c6561d1d5fa6eebbb48ca753bca20";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
