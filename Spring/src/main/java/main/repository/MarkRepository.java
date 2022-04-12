package main.repository;

import main.entity.Marks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarkRepository extends CrudRepository<Marks, Integer> {

    // оценки по предмету у студента
    @Query("select m.value from Marks m " +
            "join People p ON p.id = ?1 and m.subjects.name=?2 and p.id = m.student_id")
    List<Integer> averageMarkByStudentIdAndSubjectName(Integer stId, String subName);

    // оценки у студента по всем предметам
    @Query("select m.value, m.subjects.name from Marks m " +
            "join People p ON p.id = ?1 and m.student_id = p.id")
    List<String> marksByStudentId(Integer stId);
}
