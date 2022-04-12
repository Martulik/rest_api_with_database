package main.web;

import main.entity.Marks;
import main.exception.MarkNotFoundException;
import main.exception.PeopleNotFoundException;
import main.exception.SubjectNotFoundException;
import main.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/marks")
public class MarksController {

    private MarkService markService;

    @GetMapping("/list")
    public ResponseEntity<List<Marks>> getAllMarks() {
        try {
            return new ResponseEntity<>(markService.listMarks(), HttpStatus.OK);
        } catch (MarkNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Marks> getMark(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(markService.findMark(id), HttpStatus.OK);
        } catch (MarkNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/{id}/{name}")
    public ResponseEntity<Double> getAverageMark(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(markService.averageMark(id, name), HttpStatus.OK);
        } catch (MarkNotFoundException | SubjectNotFoundException | PeopleNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Map<String, ArrayList<Integer>>> getAverageMark(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(markService.studentMarks(id), HttpStatus.OK);
        } catch (PeopleNotFoundException | MarkNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Marks addMark(@RequestBody Marks newMark) {
        try {
            return markService.addMark(newMark);
        } catch (SubjectNotFoundException | PeopleNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMark(@PathVariable("id") Integer id) {
        try {
            markService.deleteMark(id);
        } catch (MarkNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Autowired
    public void setMarkService(MarkService markService) {
        this.markService = markService;
    }

}
