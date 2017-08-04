package org.examples.pbk.pmoviecollection;

import org.examples.pbk.pmoviecollection.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by Vitalik on 25.07.2017.
 */
@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages = {"org.examples.pbk.pmoviecollection"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
