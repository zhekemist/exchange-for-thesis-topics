package at.ac.univie.imse.backend.mariadb.rest;

import at.ac.univie.imse.backend.mariadb.projections.SuperviseesProjection;
import at.ac.univie.imse.backend.mariadb.repositories.GroupRepository;
import at.ac.univie.imse.backend.mariadb.repositories.StudentRepository;
import at.ac.univie.imse.backend.utils.ReportResult;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("mariadb & !filler & !migrator")
@CrossOrigin
@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
public class ReportEndpoints {

    ApplicationContext context;
    GroupRepository groupRepository;
    StudentRepository studentRepository;

    @GetMapping("/supervisees")
    public ResponseEntity<ReportResult<SuperviseesProjection>> superviseesReport() {
        String randomResearchGroup = groupRepository.randomResearchGroupName();
        Iterable<SuperviseesProjection> result = studentRepository.superviseesReport(randomResearchGroup);
        ReportResult<SuperviseesProjection> reportResult = ReportResult.<SuperviseesProjection>builder()
                .usedFilter(randomResearchGroup)
                .reportData(result)
                .build();
        return ResponseEntity.ok(reportResult);
    }

}
