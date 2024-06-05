package at.ac.univie.imse.backend.mariadb.filler;

import at.ac.univie.imse.backend.mariadb.datamodel.*;
import at.ac.univie.imse.backend.mariadb.repositories.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
public class DatabaseFiller {
    private final Faker faker = new Faker();
    private Random random = new Random();
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
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private TopicAssignmentRepository topicAssignmentRepository;

    @PostConstruct
    public void fillDatabaseTables() {
        fillResearchGroupTable(random.nextInt(10, 20));
        fillCategoryTable(random.nextInt(10, 20));
        fillInstructorTable(random.nextInt(20, 50));
        fillThesisTopicTable(random.nextInt(25, 60));
        fillStudentTable(random.nextInt(20, 50));
        fillTopicChoiceTable();

        log.info("Filling of the database table is completed.");
    }

    public void fillResearchGroupTable(int numberOfRecords) {
        for (int k = 0; k < numberOfRecords; k++) {
            String name = faker.company().industry();
            String researchProfile = faker.university().name();

            ResearchGroup researchGroup = new ResearchGroup(name, researchProfile);
            groupRepository.save(researchGroup);
        }
    }

    public void fillCategoryTable(int numberOfRecords) {
        Vector<Category> existingCategories = new Vector<>();
        List<Integer> indexesOfSavedCategories = new ArrayList<>();

        for (int k = 0; k < numberOfRecords; k++) {
            int indexOfRandomCategory = random.nextInt(categories.size() - 1);
            while (indexesOfSavedCategories.contains(indexOfRandomCategory)) {
                indexOfRandomCategory = random.nextInt(categories.size() - 1);
            }

            String name = categories.get(indexOfRandomCategory);

            String shortDescription = faker.programmingLanguage().name();
            Set<Category> subCategories = new HashSet<>();
            if (existingCategories.size() > 4) {
                int numberOfSubCategories = random.nextInt(4);
                for (int i = 0; i < numberOfSubCategories; i++) {
                    int indexOfRandomSubCategory = random.nextInt(existingCategories.size());
                    subCategories.add(existingCategories.get(indexOfRandomSubCategory));
                }
            }

            Category category = new Category(name, shortDescription, subCategories);
            categoryRepository.save(category);
            existingCategories.add(category);

            indexesOfSavedCategories.add(indexOfRandomCategory);
        }
    }

    public void fillInstructorTable(int numberOfRecords) {
        UserType type = UserType.INSTRUCTOR;
        Iterable<ResearchGroup> researchGroupsIterable = groupRepository.findAll();
        List<ResearchGroup> researchGroups = new ArrayList<>();
        researchGroupsIterable.forEach(researchGroups::add);

        for (int k = 0; k < numberOfRecords; k++) {
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

    public void fillThesisTopicTable(int numberOfRecords) {
        Iterable<Category> categoriesIterable = categoryRepository.findAll();
        List<Category> existingCategories = new ArrayList<>();
        categoriesIterable.forEach(existingCategories::add);
        Iterable<Instructor> instructorIterableIterable = instructorRepository.findAll();
        List<Instructor> existingInstructors = new ArrayList<>();
        instructorIterableIterable.forEach(existingInstructors::add);

        for (int k = 0; k < numberOfRecords; k++) {
            String title = faker.lorem().word();
            String description = faker.lorem().sentence();
            Set<Category> categories = new HashSet<>();
            int numberOfCategories = random.nextInt(1, 6);
            for (int i = 0; i < numberOfCategories; i++) {
                int indexOfRandomCategory = random.nextInt(existingCategories.size());
                categories.add(existingCategories.get(indexOfRandomCategory));
            }
            Map<Long, LiteratureReference> references = new HashMap<>();
            int numberOfReferences = random.nextInt(21);
            for (long i = 0; i < numberOfReferences; i++) {
                int referenceYear = random.nextInt(1975, 2024);
                String referenceTitle = faker.lorem().word();
                String referenceLink = faker.internet().url();
                String referenceAuthor = faker.name().fullName();
                LiteratureReference reference = new LiteratureReference(referenceYear, referenceTitle, referenceLink, referenceAuthor);
                references.put(i, reference);
            }

            Instructor instructor = existingInstructors.get(random.nextInt(existingInstructors.size()));

            ThesisTopic thesisTopic = new ThesisTopic(title, description, categories, references, instructor);
            topicRepository.save(thesisTopic);

            handleThesisTopicSave(thesisTopic);
        }
    }

    public void handleThesisTopicSave(ThesisTopic topic) {
        for (var referenceEntry : topic.getReferences().entrySet()) {
            LiteratureReference reference = referenceEntry.getValue();
            reference.setReferenceNumber(referenceEntry.getKey());
            reference.setTopic(topic);
            referenceRepository.save(reference);
        }
    }

    public void fillStudentTable(int numberOfRecords) {
        UserType type = UserType.STUDENT;
        Iterable<ThesisTopic> thesisTopicIterable = topicRepository.findAll();
        List<ThesisTopic> thesisTopics = new ArrayList<>();
        thesisTopicIterable.forEach(thesisTopics::add);

        for (int k = 0; k < numberOfRecords; k++) {
            String username = faker.name().username();
            Name name = new Name(faker.name().firstName(), faker.name().lastName());
            String email = faker.internet().emailAddress();
            String password = faker.internet().password();
            String studyProgram = faker.educator().course();
            int matriculationNumber = random.nextInt(10000000, 99999999);
            Set<TopicChoice> choices = new HashSet<>();

            Set<ThesisTopic> bookmarkedTopics = new HashSet<>();
            int numberOfBookmarkedThesisTopics = random.nextInt(7);
            for (long i = 0; i < numberOfBookmarkedThesisTopics; i++) {
                ThesisTopic topicToBookmark = thesisTopics.get(random.nextInt(thesisTopics.size() - 1));
                bookmarkedTopics.add(topicToBookmark);
            }

            Student student = new Student(type, username, name, email, password, studyProgram, matriculationNumber, choices, bookmarkedTopics);

            AssignedTopic assignedThesisTopic = null;


            studentRepository.save(student);

            if (random.nextInt(10) == 1) {
                Iterable<AssignedTopic> assignedThesisTopicRealtionIterable = topicAssignmentRepository.findAll();
                List<AssignedTopic> assignedThesisTopicsRealtion = new ArrayList<>();
                assignedThesisTopicRealtionIterable.forEach(assignedThesisTopicsRealtion::add);
                List<Long> assignedThesisTopicsIds = new ArrayList<>();
                for (AssignedTopic existingAssignedThesisTopic : assignedThesisTopicsRealtion)
                    assignedThesisTopicsIds.add(existingAssignedThesisTopic.getTopic().getTopicId());

                ThesisTopic topicToAssign = thesisTopics.get(random.nextInt(thesisTopics.size() - 1));
                if (!assignedThesisTopicsIds.contains(topicToAssign.getTopicId())) {
                    String reason = faker.lorem().word();
                    assignedThesisTopic = new AssignedTopic(student, topicToAssign, reason);
                    topicAssignmentRepository.save(assignedThesisTopic);
                }
            }
        }
    }


    public void fillTopicChoiceTable() {
        Iterable<ThesisTopic> thesisTopicIterable = topicRepository.findAll();
        List<ThesisTopic> thesisTopics = new ArrayList<>();
        thesisTopicIterable.forEach(thesisTopics::add);
        Iterable<Student> studentIterable = studentRepository.findAll();
        List<Student> students = new ArrayList<>();
        studentIterable.forEach(students::add);

        for (int k = 0; k < students.size(); k++) {
            int remainingPriorityPoints = 1000;
            Student student = students.get(k);

            int numberOfChoices = random.nextInt(6);
            for (int i = 0; i < numberOfChoices; i++) {
                if (remainingPriorityPoints <= 1)
                    break;

                LocalDateTime timestamp = LocalDateTime.now().minusSeconds(random.nextInt(0, 60 * 60 * 24 * 365));
                int usedPriorityPoints = random.nextInt(1, remainingPriorityPoints);
                ThesisTopic topic = thesisTopics.get(random.nextInt(thesisTopics.size() - 1));

                TopicChoice topicChoice = new TopicChoice(timestamp, usedPriorityPoints, topic, student);
                choiceRepository.save(topicChoice);
                remainingPriorityPoints = remainingPriorityPoints - usedPriorityPoints;
            }
        }
    }

    private List<String> categories = Arrays.asList("Artificial Intelligence", "Machine Learning", "Data Science", "Cybersecurity",
            "Software Engineering", "Computer Networks", "Databases", "Human-Computer Interaction", "Computer Graphics", "Robotics",
            "Algorithms and Data Structures", "Operating Systems", "Distributed Systems", "Cloud Computing", "Internet of Things (IoT)",
            "Natural Language Processing", "Bioinformatics", "Blockchain Technology", "Embedded Systems", "Game Development", "Computer Vision",
            "Quantum Computing", "Software Testing and Quality Assurance", "Big Data", "Information Systems", "Theory of Computation"
    );
}
