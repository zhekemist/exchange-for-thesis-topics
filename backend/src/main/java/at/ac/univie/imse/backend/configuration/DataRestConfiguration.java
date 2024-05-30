package at.ac.univie.imse.backend.configuration;

import at.ac.univie.imse.backend.configuration.repodetection.AnnotatedRepositoryDetectionStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
@RequiredArgsConstructor
public class DataRestConfiguration implements RepositoryRestConfigurer {

    private final Environment springEnv;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        config.setRepositoryDetectionStrategy(new AnnotatedRepositoryDetectionStrategy(this.springEnv));
        cors.addMapping("/**");
    }
}
