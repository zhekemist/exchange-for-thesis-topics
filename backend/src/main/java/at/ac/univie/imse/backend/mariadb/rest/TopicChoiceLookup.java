package at.ac.univie.imse.backend.mariadb.rest;

import at.ac.univie.imse.backend.mariadb.datamodel.TopicChoice;
import at.ac.univie.imse.backend.mariadb.datamodel.TopicChoiceID;
import at.ac.univie.imse.backend.mariadb.repositories.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.support.EntityLookupSupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TopicChoiceLookup extends EntityLookupSupport<TopicChoice> {
    @Autowired
    ChoiceRepository choiceRepository;

    @Override
    public Object getResourceIdentifier(TopicChoice entity) {
        return String.format("%d_%d", entity.getTopic().getTopicId(), entity.getStudent().getUserId());
    }

    @Override
    public Optional<TopicChoice> lookupEntity(Object id) {
        String idStr = id.toString();
        int idx = idStr.indexOf('_');
        long topicID = Long.parseLong(idStr.substring(0, idx));
        long studentId = Long.parseLong(idStr.substring(idx + 1));
        return choiceRepository.findById(new TopicChoiceID(topicID, studentId));
    }

    @Override
    public Optional<String> getLookupProperty() {
        return Optional.empty();
    }
}
