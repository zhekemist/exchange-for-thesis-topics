package at.ac.univie.imse.backend.mongodb.migrator;

import at.ac.univie.imse.backend.mariadb.datamodel.Category;
import at.ac.univie.imse.backend.mariadb.datamodel.Instructor;
import at.ac.univie.imse.backend.mariadb.datamodel.ThesisTopic;
import at.ac.univie.imse.backend.mariadb.repositories.*;
import at.ac.univie.imse.backend.mongodb.repositories.CategoryMongoRepository;
import at.ac.univie.imse.backend.mongodb.repositories.InstructorMongoRepository;
import at.ac.univie.imse.backend.mongodb.repositories.StudentMongoRepository;
import at.ac.univie.imse.backend.mongodb.repositories.ThesisTopicMongoRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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

    @PostConstruct
    public void migrateDataToMongoDB() {
        clearDatabase();

        log.info(String.valueOf("Migration stared"));
        migrateCategories();
        migrateInstructors();
        migrateThesisTopics();
        migrateStudents();
        log.info(String.valueOf("Migration finished"));
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

    }

    public void clearDatabase() {
        categoryMongoRepository.deleteAll();
        instructorMongoRepository.deleteAll();
        studentMongoRepository.deleteAll();
        thesisTopicMongoRepository.deleteAll();
    }
}
