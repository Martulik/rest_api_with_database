package main.repository;

import main.entity.People;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeopleRepository extends CrudRepository<People, Integer> {
    //одногруппники по id группы
    @Query("SELECT p FROM Groups g INNER JOIN People p ON p.group_id=g.id and g.id = ?1")
    List<People> listPeopleByGroupId(Integer id);

    //одногруппники по названию группы
    @Query("SELECT p FROM Groups g INNER JOIN People p ON p.group_id=g.id and g.name = ?1")
    List<People> listPeopleByGroupName(String groupName);

    //какой учитель поставил оценку
    @Query("SELECT p FROM Marks m INNER JOIN People p ON p.id=m.teacher_id and m.id = ?1")
    People getTeacherByMarkId(Integer id);

    // количество студентов в группе по ее имени
    @Query("select count(distinct p) FROM People p JOIN Groups g ON p.group_id = g.id and g.name=?1")
    Integer getCountStudentsByGroupName(String name);

}
