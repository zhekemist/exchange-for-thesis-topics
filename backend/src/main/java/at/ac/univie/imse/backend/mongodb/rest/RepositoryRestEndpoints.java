package at.ac.univie.imse.backend.mongodb.rest;

import at.ac.univie.imse.backend.mongodb.datamodel.Student;
import at.ac.univie.imse.backend.mongodb.datamodel.TopicChoice;
import at.ac.univie.imse.backend.mongodb.repositories.StudentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Profile("mongodb")
@RepositoryRestController
public class RepositoryRestEndpoints {
    @Autowired
    StudentMongoRepository studentRepository;

    @PostMapping("/students/{id}/choices")
    public ResponseEntity<Long> addTopicChoice(@PathVariable("id") Student student, @RequestBody TopicChoice topicChoice) {
        var studentChoices = student.getTopicChoices();
        studentChoices.add(topicChoice);
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
