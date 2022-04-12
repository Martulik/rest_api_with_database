package main.repository;

import main.entity.Subjects;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subjects, Integer> {
    @Query("select (count(s) > 0) from Subjects s where s.name = ?1")
    boolean existsByName(String name);
}
