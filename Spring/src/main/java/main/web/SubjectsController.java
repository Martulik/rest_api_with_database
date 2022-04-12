package main.web;

import main.entity.Subjects;
import main.exception.SubjectNotFoundException;
import main.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectsController {

    private SubjectService subjectService;

    @GetMapping("/list")
    public ResponseEntity<List<Subjects>> getAllSubjects() {
        try {
            return new ResponseEntity<>(subjectService.listSubjects(), HttpStatus.OK);
        } catch (SubjectNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Subjects> getSubject(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(subjectService.findSubject(id), HttpStatus.OK);
        } catch (SubjectNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Subjects addSubject(@RequestBody Subjects newSubject) {
        return subjectService.addSubject(newSubject);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSubject(@PathVariable("id") Integer id) {
        try {
            subjectService.deleteSubject(id);
        } catch (SubjectNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

}
