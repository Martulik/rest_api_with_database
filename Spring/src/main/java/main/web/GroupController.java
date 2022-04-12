package main.web;

import main.entity.Groups;
import main.exception.GroupNotFoundException;
import main.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private GroupService groupService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Groups addGroup(@RequestBody Groups newGrp) {
        return groupService.addGroup(newGrp);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Groups>> getAllGroups() {
        try {
            return new ResponseEntity<>(groupService.listGroups(), HttpStatus.OK);
        } catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Groups> getGroup(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(groupService.findGroup(id), HttpStatus.OK);
        } catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/list/name/{name}")
    public ResponseEntity<Groups> getGroup(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(groupService.findGroupByName(name), HttpStatus.OK);
        } catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGroup(@PathVariable("id") Integer id) {
        try {
            groupService.deleteGroup(id);
        } catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
}
