package at.ac.univie.imse.backend.mongodb.migrator;

import at.ac.univie.imse.backend.mariadb.datamodel.*;
import at.ac.univie.imse.backend.mariadb.repositories.*;
import at.ac.univie.imse.backend.mongodb.repositories.CategoryMongoRepository;
import at.ac.univie.imse.backend.mongodb.repositories.InstructorMongoRepository;
import at.ac.univie.imse.backend.mongodb.repositories.StudentMongoRepository;
import at.ac.univie.imse.backend.mongodb.repositories.ThesisTopicMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Profile("migrator")
@Component
public class DataMigrator {
    @Autowired
    private CategoryMongoRepository categoryMongoRepository;
    @Autowired
    private InstructorMongoRepository instructorMongoRepository;
    @Autowired
    private StudentMongoRepository studentMongoRepository;
    @Autowired
    private ThesisTopicMongoRepository thesisTopicMongoRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TopicAssignmentRepository topicAssignmentRepository;

    @Autowired
    ApplicationContext context;

    @EventListener(ApplicationStartedEvent.class)
    public void migrateDataToMongoDB() {
        clearDatabase();

        log.info(String.valueOf("Migration stared"));
        migrateCategories();
        migrateInstructors();
        migrateThesisTopics();
        migrateStudents();
        log.info(String.valueOf("Migration finished"));

        SpringApplication.exit(context, () -> 0);
    }

    public void migrateCategories() {
        Iterable<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            at.ac.univie.imse.backend.mongodb.datamodel.Category categorytoAdd = new at.ac.univie.imse.backend.mongodb.datamodel.Category(category);
            categoryMongoRepository.save(categorytoAdd);
        }
    }

    public void migrateInstructors() {
        Iterable<Instructor> instructors = instructorRepository.findAll();

        for (Instructor instructor : instructors) {
            at.ac.univie.imse.backend.mongodb.datamodel.Instructor instructorToAdd = new at.ac.univie.imse.backend.mongodb.datamodel.Instructor(instructor);
            instructorMongoRepository.save(instructorToAdd);
        }

    }

    public void migrateThesisTopics() {
        Iterable<ThesisTopic> thesisTopics = topicRepository.findAll();

        for (ThesisTopic thesisTopic : thesisTopics) {
            at.ac.univie.imse.backend.mongodb.datamodel.ThesisTopic thesisTopicZoAdd = new at.ac.univie.imse.backend.mongodb.datamodel.ThesisTopic(thesisTopic);
            thesisTopicMongoRepository.save(thesisTopicZoAdd);
        }

    }

    public void migrateStudents() {
        Iterable<Student> students = studentRepository.findAll();

        for (Student student : students) {
            List<AssignedTopic> assignedTopic = topicAssignmentRepository.findByStudent_UserId(student.getUserId());
            at.ac.univie.imse.backend.mongodb.datamodel.Student studentTioAdd = new at.ac.univie.imse.backend.mongodb.datamodel.Student(student, assignedTopic);
            studentMongoRepository.save(studentTioAdd);
        }

    }

    public void clearDatabase() {
        categoryMongoRepository.deleteAll();
        instructorMongoRepository.deleteAll();
        studentMongoRepository.deleteAll();
        thesisTopicMongoRepository.deleteAll();
    }
}
