package at.ac.univie.imse.backend.mariadb.rest;

import at.ac.univie.imse.backend.mariadb.datamodel.Student;
import at.ac.univie.imse.backend.mariadb.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestController
public class CustomRestEndpoints {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students/{id}/priorityPoints")
    public ResponseEntity<Long> getPriorityPoints(@PathVariable("id") Student student) {
        return ResponseEntity.ok(studentRepository.priorityPoints(student.getUserId()));
    }
}
