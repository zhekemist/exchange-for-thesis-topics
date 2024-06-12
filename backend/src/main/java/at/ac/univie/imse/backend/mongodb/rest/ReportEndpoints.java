package at.ac.univie.imse.backend.mongodb.rest;

import at.ac.univie.imse.backend.mongodb.projections.SuperviseesProjection;
import at.ac.univie.imse.backend.mongodb.repositories.InstructorMongoRepository;
import at.ac.univie.imse.backend.mongodb.repositories.StudentMongoRepository;
import at.ac.univie.imse.backend.utils.ReportResult;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("mongodb")
@CrossOrigin
@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
public class ReportEndpoints {

    ApplicationContext context;
    InstructorMongoRepository instructorRepository;
    StudentMongoRepository studentRepository;

    @GetMapping("/supervisees")
    public ResponseEntity<ReportResult<SuperviseesProjection>> superviseesReport() {
        String randomResearchGroup = instructorRepository.randomResearchGroupName();
        Iterable<at.ac.univie.imse.backend.mongodb.projections.SuperviseesProjection> result = studentRepository.supervisees(randomResearchGroup);
        ReportResult<SuperviseesProjection> reportResult = ReportResult.<SuperviseesProjection>builder()
                .usedFilter(randomResearchGroup)
                .reportData(result)
                .build();
        return ResponseEntity.ok(reportResult);
    }

}
