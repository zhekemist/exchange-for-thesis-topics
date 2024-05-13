package at.ac.univie.imse.backend.configuration.repodetection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;

@Slf4j
@RequiredArgsConstructor
public class AnnotatedRepositoryDetectionStrategy implements RepositoryDetectionStrategy {

    private final Environment springEnv;

    @Override
    public boolean isExported(RepositoryMetadata metadata) {
        Class<?> repositoryInterface = metadata.getRepositoryInterface();
        ExposeViaRestIf exposeViaRestIfAnnotation = repositoryInterface.getAnnotation(ExposeViaRestIf.class);

        boolean expose = (exposeViaRestIfAnnotation != null) &&
                this.springEnv.getProperty(exposeViaRestIfAnnotation.value(), boolean.class, false);

        if (expose) {
            log.info("{} will be exposed.", repositoryInterface.getTypeName());
        }

        return expose;
    }
}
