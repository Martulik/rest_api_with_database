package main.service;

import main.entity.People;

import java.util.List;

public interface PeopleService {
    List<People> listPeople();

    People findPeople(Integer id);

    People addPeople(People people);

    //одногруппники по id группы
    List<People> getStudentsFromGroupId(Integer id);

    //одногруппники по названию группы
    List<People> getStudentsByGroupName(String groupName);

    //какой учитель поставил оценку
    People getTeacherByMarkId(Integer id);

    // количество студентов в группе по ее имени
    Integer getCountStudents(String grpName);

    void deletePeople(Integer id);
}
