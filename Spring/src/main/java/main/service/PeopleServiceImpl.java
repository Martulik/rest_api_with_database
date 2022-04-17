package main.service;

import main.entity.People;
import main.exception.GroupNotFoundException;
import main.exception.MarkNotFoundException;
import main.exception.PeopleNotFoundException;
import main.repository.GroupRepository;
import main.repository.MarkRepository;
import main.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private GroupRepository grpRepository;
    @Autowired
    private MarkRepository markRepository;

    @Override
    public List<People> listPeople() {
        List<People> people = (List<People>) peopleRepository.findAll();
        if (people.isEmpty()) {
            throw new PeopleNotFoundException("People not found");
        }
        return people;
    }

    @Override
    public People findPeople(Integer id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        if (optionalPeople.isPresent()) {
            return optionalPeople.get();
        } else {
            throw new PeopleNotFoundException("People not found");
        }
    }

    @Override
    public People addPeople(People people) {
        if (grpRepository.existsById(people.getGroup_id())) {
            return peopleRepository.save(people);
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

    @Override
    public List<People> getStudentsFromGroupId(Integer id) {
        List<People> people = peopleRepository.listPeopleByGroupId(id);
        if (!grpRepository.existsById(id)) {
            throw new GroupNotFoundException("Group not found");
        }
        if (people.isEmpty()) {
            throw new PeopleNotFoundException("People not found");
        }
        return people;
    }

    @Override
    public List<People> getStudentsByGroupName(String groupName) {
        List<People> people = peopleRepository.listPeopleByGroupName(groupName);
        if (!Optional.ofNullable(grpRepository.getGroupsByName(groupName)).isPresent()) {
            throw new GroupNotFoundException("Group not found");
        }
        if (people.isEmpty()) {
            throw new PeopleNotFoundException("People not found");
        }
        return people;
    }

    @Override
    public People getTeacherByMarkId(Integer id) {
        if (!markRepository.existsById(id)) {
            throw new MarkNotFoundException("Mark not found");
        }
        return peopleRepository.getTeacherByMarkId(id);
    }

    @Override
    public Integer getCountStudents(String grpName) {
        if (!Optional.ofNullable(grpRepository.getGroupsByName(grpName)).isPresent()) {
            throw new GroupNotFoundException("Group not found");
        }
        return peopleRepository.getCountStudentsByGroupName(grpName);
    }

    @Override
    public void deletePeople(Integer id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        if (optionalPeople.isPresent()) {
            peopleRepository.deleteById(id);
        } else {
            throw new PeopleNotFoundException("People not found");
        }
    }
}
