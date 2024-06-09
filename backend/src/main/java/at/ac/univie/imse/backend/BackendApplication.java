package at.ac.univie.imse.backend;

import at.ac.univie.imse.backend.mongodb.datamodel.*;
import at.ac.univie.imse.backend.mongodb.repositories.CategoryMongoRepository;
import at.ac.univie.imse.backend.mongodb.repositories.InstructorMongoRepository;
import at.ac.univie.imse.backend.mongodb.repositories.StudentMongoRepository;
import at.ac.univie.imse.backend.mongodb.repositories.ThesisTopicMongoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CategoryMongoRepository categoryMongoRepository, InstructorMongoRepository instructorMongoRepository,
                                        ThesisTopicMongoRepository thesisTopicMongoRepository, StudentMongoRepository studentMongoRepository) {
        return args -> {
            MongoCategory category1 = new MongoCategory("test", "c", null, new HashSet<>());
            MongoCategory category2 = new MongoCategory("w", "c", null, new HashSet<>());
            categoryMongoRepository.save(category1);
            categoryMongoRepository.save(category2);

            Set<MongoCategory> subCategories = new HashSet<>();
            subCategories.add(category1);
            subCategories.add(category2);
            MongoCategory category = new MongoCategory("test", "description", category2, subCategories);
            categoryMongoRepository.save(category);

            MongoResearchGroup researchGroup = new MongoResearchGroup("testGroup", "test");

            MongoLiteratureReference literatureReference = new MongoLiteratureReference("title", "author", 2011, "link");

            MongoInstructor instructor = new MongoInstructor("test", "uname", "pword", new MongoName("fname", "lname"), "cinfo", true, researchGroup);
            instructorMongoRepository.save(instructor);

            Set<MongoLiteratureReference> literatureReferenceMap = new HashSet<>();
            literatureReferenceMap.add(literatureReference);
            MongoThesisTopic thesisTopic = new MongoThesisTopic("test", "descr", instructor, subCategories, literatureReferenceMap);
            thesisTopicMongoRepository.save(thesisTopic);


            Set topics = new HashSet<>();
            topics.add(thesisTopic);
            MongoAssignedTopic assignedTopic = new MongoAssignedTopic("reason", thesisTopic);
            MongoStudent student = new MongoStudent("email", "uname", "pword", new MongoName("r", "r"), 12121212, "stdyprog", topics, topics, assignedTopic);
            studentMongoRepository.save(student);
        };
    }

}
