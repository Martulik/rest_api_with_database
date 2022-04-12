package main.repository;

import main.entity.Groups;
import main.entity.People;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Groups, Integer> {
    @Query("select g from Groups g where g.name = ?1")
    Groups getGroupsByName(String name);
}
