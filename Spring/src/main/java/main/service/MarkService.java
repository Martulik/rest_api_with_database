package main.service;

import main.entity.Marks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MarkService {
    List<Marks> listMarks();

    Marks findMark(Integer id);

    Marks addMark(Marks mark);

    // средняя оценка по предмету у студента
    Double averageMark(Integer stId, String subName);

    // оценки у студента по всем предметам
    Map<String, ArrayList<Integer>> studentMarks(Integer id);

    void deleteMark(Integer id);

}
