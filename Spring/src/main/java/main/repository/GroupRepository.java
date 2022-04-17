package main.repository;

import main.entity.Groups;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Groups, Integer> {
    @Query("select g from Groups g where g.name = ?1")
    Groups getGroupsByName(String name);
}
