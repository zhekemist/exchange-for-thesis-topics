package at.ac.univie.imse.backend.mariadb.rest;

import at.ac.univie.imse.backend.mariadb.datamodel.LiteratureReference;
import at.ac.univie.imse.backend.mariadb.datamodel.ThesisTopic;
import at.ac.univie.imse.backend.mariadb.repositories.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Profile("mariadb")
@Component
@RepositoryEventHandler
public class ThesisTopicEventHandler {

    @Autowired
    ReferenceRepository referenceRepository;

    @HandleAfterCreate
    public void handleThesisTopicSave(ThesisTopic topic) {
        for (var referenceEntry : topic.getReferences().entrySet()) {
            LiteratureReference reference = referenceEntry.getValue();
            reference.setReferenceNumber(referenceEntry.getKey());
            reference.setTopic(topic);
            referenceRepository.save(reference);
        }
    }
}
