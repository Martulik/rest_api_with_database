package main.service;

import main.entity.Subjects;

import java.util.List;

public interface SubjectService {
    List<Subjects> listSubjects();

    Subjects findSubject(Integer id);

    Subjects addSubject(Subjects subject);

    void deleteSubject(Integer id);

}
