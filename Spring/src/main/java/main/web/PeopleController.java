package main.web;

import main.entity.People;
import main.exception.GroupNotFoundException;
import main.exception.MarkNotFoundException;
import main.exception.PeopleNotFoundException;
import main.exception.SubjectNotFoundException;
import main.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/people")
public class PeopleController {


    private PeopleService peopleService;

    @GetMapping("/list")
    public ResponseEntity<List<People>> getAllPeople() {
        try {
            return new ResponseEntity<>(peopleService.listPeople(), HttpStatus.OK);
        } catch (PeopleNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<People> getPeople(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(peopleService.findPeople(id), HttpStatus.OK);
        } catch (PeopleNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/list/groupId/{id}")
    public ResponseEntity<List<People>> getPeopleByGroupId(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(peopleService.getStudentsFromGroupId(id), HttpStatus.OK);
        } catch (PeopleNotFoundException | GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/list/groupName/{name}")
    public ResponseEntity<List<People>> getPeopleByGroupName(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(peopleService.getStudentsByGroupName(name), HttpStatus.OK);
        } catch (PeopleNotFoundException | GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/mark/{id}")
    public ResponseEntity<People> getPeopleByMarkId(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(peopleService.getTeacherByMarkId(id), HttpStatus.OK);
        } catch (MarkNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/students/count/{name}")
    public ResponseEntity<Integer> getCountStudentsInGroup(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(peopleService.getCountStudents(name), HttpStatus.OK);
        } catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public People addPeople(@RequestBody People newPeople) {
        try {
            return peopleService.addPeople(newPeople);
        } catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletePeople(@PathVariable("id") Integer id) {
        try {
            peopleService.deletePeople(id);
        } catch (PeopleNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Autowired
    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


}
