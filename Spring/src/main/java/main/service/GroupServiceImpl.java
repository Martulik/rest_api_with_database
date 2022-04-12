package main.service;

import main.entity.Groups;
import main.exception.GroupNotFoundException;
import main.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository grpRepository;

    @Override
    public List<Groups> listGroups() {
        List<Groups> grp = (List<Groups>) grpRepository.findAll();
        if (grp.isEmpty()) {
            throw new GroupNotFoundException("Groups not found");
        }
        return grp;
    }

    @Override
    public Groups findGroup(Integer id) {
        Optional<Groups> optionalGrp = grpRepository.findById(id);
        if (optionalGrp.isPresent()) {
            return optionalGrp.get();
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

    @Override
    public Groups findGroupByName(String name) {
        Optional<Groups> optionalGrp = Optional.ofNullable(grpRepository.getGroupsByName(name));
        if (optionalGrp.isPresent()) {
            return optionalGrp.get();
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

    @Override
    public Groups addGroup(Groups group) {
        return grpRepository.save(group);
    }

    @Override
    public void deleteGroup(Integer id) {
        Optional<Groups> optionalGrp = grpRepository.findById(id);
        if (optionalGrp.isPresent()) {
            grpRepository.deleteById(id);
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

}
