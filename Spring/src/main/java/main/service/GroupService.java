package main.service;

import main.entity.Groups;

import java.util.List;

public interface GroupService {
    List<Groups> listGroups();

    Groups findGroup(Integer id);

    Groups findGroupByName(String name);

    Groups addGroup(Groups group);

    void deleteGroup(Integer id);

}
