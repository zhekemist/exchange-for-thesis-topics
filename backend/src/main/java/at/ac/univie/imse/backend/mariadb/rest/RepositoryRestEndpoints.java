package at.ac.univie.imse.backend.mariadb.rest;

import at.ac.univie.imse.backend.mariadb.repositories.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@Profile("mariadb")
@RepositoryRestController
public class RepositoryRestEndpoints {
    @Autowired
    ChoiceRepository choiceRepository;

//    @PostMapping("/students/{id}/choices")
//    public ResponseEntity<Long> addTopicChoice(@PathVariable("id") Student student, @RequestBody TopicChoice topicChoice) {
//        topicChoice.setStudent(student);
//        System.out.println(topicChoice.getTopic().getTopicId());
//        //choiceRepository.save(topicChoice);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
}
