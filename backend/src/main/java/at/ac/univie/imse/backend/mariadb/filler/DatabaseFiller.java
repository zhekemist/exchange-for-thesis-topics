package at.ac.univie.imse.backend.mariadb.filler;

import at.ac.univie.imse.backend.mariadb.datamodel.*;
import at.ac.univie.imse.backend.mariadb.repositories.*;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseFiller {
    private final Faker faker = new Faker();
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private TopicRepository topicRepository;

    @PostConstruct
    public void fillDatabaseTables() {
//        fillResearchGroupTable(5);
//        fillCategoryTable(20);
//        fillInstructorTable(10);
        fillThesisTopicTable(20);
//        fillLiteratureReferenceTable(20);
    }

    public void fillResearchGroupTable(int numberOfRecords) {
        for(int k=0;k<numberOfRecords;k++) {
            String name = faker.company().industry();
            String researchProfile = faker.university().name();

            ResearchGroup researchGroup = new ResearchGroup(name, researchProfile);
            groupRepository.save(researchGroup);
        }
    }

    public void fillCategoryTable(int numberOfRecords) {
        Vector<Category> existingCategories = new Vector<>();
        Random random = new Random();
        for(int k=0;k<numberOfRecords;k++) {
            String name = faker.educator().course();
            String shortDescription = faker.programmingLanguage().name();
            Set<Category> subCategories = new HashSet<>();
            if(existingCategories.size()>4){
                int numberOfSubCategories = random.nextInt(4);
                for(int i=0;i<numberOfSubCategories;i++){
                    int indexOfRandomCategory = random.nextInt(existingCategories.size());
                    subCategories.add(existingCategories.get(indexOfRandomCategory));
                }
            }

            Category category = new Category(name, shortDescription,subCategories);
            categoryRepository.save(category);
            existingCategories.add(category);
        }
    }

    public void fillInstructorTable(int numberOfRecords) {
        UserType type= UserType.INSTRUCTOR;
        Iterable<ResearchGroup> researchGroupsIterable = groupRepository.findAll();
        List<ResearchGroup> researchGroups = new ArrayList<>();
        researchGroupsIterable.forEach(researchGroups::add);
        Random random = new Random();

        for(int k=0;k<numberOfRecords;k++) {
            String username = faker.name().username();
            Name name = new Name(faker.name().firstName(), faker.name().lastName());
            String email = faker.internet().emailAddress();
            String password = faker.internet().password();
            String contactInformation = faker.address().fullAddress();
            boolean isAdmin = faker.bool().bool();
            ResearchGroup researchGroup = researchGroups.get(random.nextInt(researchGroups.size()));

            Instructor instructor = new Instructor(type, username, name, email, password, contactInformation, isAdmin, researchGroup);
            instructorRepository.save(instructor);
        }
    }

    public void fillLiteratureReferenceTable(int numberOfRecords) {
        Random random = new Random();
        for(int k=0;k<numberOfRecords;k++) {
            int year = random.nextInt(1975, 2024);
            String title = faker.book().title();
            String link = faker.internet().url();
            String author = faker.name().fullName();

            LiteratureReference literatureReference = new LiteratureReference(year, title, link, author);
            referenceRepository.save(literatureReference);
        }
    }

    public void fillThesisTopicTable(int numberOfRecords) {
        Random random = new Random();
        Iterable<Category> categoriesIterable = categoryRepository.findAll();
        List<Category> existingCategories = new ArrayList<>();
        categoriesIterable.forEach(existingCategories::add);
        Iterable<LiteratureReference> researchGroupsIterable = referenceRepository.findAll();
        List<LiteratureReference> existingReferences = new ArrayList<>();
        researchGroupsIterable.forEach(existingReferences::add);
        Iterable<Instructor> instructorIterableIterable = instructorRepository.findAll();
        List<Instructor> existingInstructors = new ArrayList<>();
        instructorIterableIterable.forEach(existingInstructors::add);

        for(int k=0;k<numberOfRecords;k++) {
            String title = faker.lorem().word();
            String description = faker.lorem().sentence();
            Set<Category> categories = new HashSet<>();
            int numberOfCategories = random.nextInt(4);
            for(int i = 0; i< numberOfCategories; i++){
                int indexOfRandomCategory = random.nextInt(existingCategories.size());
                categories.add(existingCategories.get(indexOfRandomCategory));
            }
            Map<Long, LiteratureReference> references = new HashMap<>();
//            int numberOfReferences = random.nextInt(4);
//            for(int i = 0; i< numberOfReferences; i++) {
//                int indexOfRandomReference = random.nextInt(existingReferences.size());
//                LiteratureReference referedReference = existingReferences.get(indexOfRandomReference);
//                references.put(referedReference.getReferenceNumber(), referedReference);
//            }

            LiteratureReference r = new LiteratureReference(212,"sdf","safsd","sdf");
            references.put(1L,r);
            Instructor instructor = existingInstructors.get(random.nextInt(existingInstructors.size()));

            ThesisTopic thesisTopic = new ThesisTopic(title, description, categories, references, instructor);
            topicRepository.save(thesisTopic);
            referenceRepository.save(r);

        }
    }

//    public void fillTopicChoiceTable(int numberOfRecords) {
//        Random random = new Random();
//        Iterable<ThesisTopic> thesisTopicIterable = topicRepository.findAll();
//        List<ThesisTopic> thesisTopics = new ArrayList<>();
//        thesisTopicIterable.forEach(thesisTopics::add);
//        Iterable<ResearchGroup> researchGroupsIterable = groupRepository.findAll();
//        Map<Long, LiteratureReference> references = new HashMap<>();
//
//        for(int k=0;k<numberOfRecords;k++) {
//            String title = faker.lorem().word();
//            String description = faker.lorem().sentence();
//            Category category = categories.get(random.nextInt(categories.size()-1));
//
//            LiteratureReference literatureReference = new LiteratureReference(year, title, link, author);
//            referenceRepository.save(literatureReference);
//        }
//    }


}
