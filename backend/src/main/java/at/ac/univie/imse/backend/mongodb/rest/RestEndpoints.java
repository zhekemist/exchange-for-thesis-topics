package at.ac.univie.imse.backend.mongodb.rest;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Profile("mongodb")
@RestController
@RequestMapping("/api-meta")
public class RestEndpoints {
    @GetMapping("/version")
    public ResponseEntity<String> getVersions() {
        return ResponseEntity.ok("\"MONGODB\"");
    }
}
