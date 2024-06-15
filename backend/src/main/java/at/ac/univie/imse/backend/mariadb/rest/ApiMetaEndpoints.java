package at.ac.univie.imse.backend.mariadb.rest;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Profile("mariadb & !filler & !migrator")
@CrossOrigin
@RestController
@RequestMapping("/api/meta")
@AllArgsConstructor
public class ApiMetaEndpoints {

    ApplicationContext context;

    @GetMapping("/version")
    public ResponseEntity<String> getVersions() {
        return ResponseEntity.ok("\"MARIADB\"");
    }

    @PostMapping("/migrate")
    public ResponseEntity<String> migrate() {
        SpringApplication.exit(context, () -> 0);
        return ResponseEntity.accepted().build(); // not reached anyways
    }
}
