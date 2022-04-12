package main.service;

import main.entity.Marks;
import main.exception.MarkNotFoundException;
import main.exception.PeopleNotFoundException;
import main.exception.SubjectNotFoundException;
import main.repository.MarkRepository;
import main.repository.PeopleRepository;
import main.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Marks> listMarks() {
        List<Marks> marks = (List<Marks>) markRepository.findAll();
        if (marks.isEmpty()) {
            throw new MarkNotFoundException("Marks not found");
        }
        return marks;
    }

    @Override
    public Marks findMark(Integer id) {
        Optional<Marks> optionalMarks = markRepository.findById(id);
        if (optionalMarks.isPresent()) {
            return optionalMarks.get();
        } else {
            throw new MarkNotFoundException("Mark not found");
        }
    }

    @Override
    public Marks addMark(Marks marks) {
        if (!peopleRepository.existsById(marks.getStudent_id()) || !peopleRepository.existsById(marks.getTeacher_id())) {
            throw new PeopleNotFoundException("People not found");
        }
        if (!subjectRepository.existsById(marks.getSubject_id())) {
            throw new SubjectNotFoundException("Subject not found");
        }
        return markRepository.save(marks);
    }

    @Override
    public Double averageMark(Integer stId, String subName) {
        OptionalDouble optionalMarks = markRepository.averageMarkByStudentIdAndSubjectName(stId, subName)
                .stream()
                .mapToInt((s) -> Integer.parseInt(String.valueOf(s)))
                .average();
        if (!peopleRepository.existsById(stId)) {
            throw new PeopleNotFoundException("People not found");
        }
        if (!subjectRepository.existsByName(subName)) {
            throw new SubjectNotFoundException("Subject not found");
        }
        if (optionalMarks.isPresent()) {
            return optionalMarks.getAsDouble();
        } else {
            throw new MarkNotFoundException("Marks not found");
        }
    }

    @Override
    public Map<String, ArrayList<Integer>> studentMarks(Integer id) {
        if (!peopleRepository.existsById(id)) {
            throw new PeopleNotFoundException("Student not found");
        }
        List<String> list = markRepository.marksByStudentId(id);
        if (list.isEmpty()) {
            throw new MarkNotFoundException("Marks not found");
        }
        Map<String, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < list.size(); ++i) {
            Integer substrMark = Integer.valueOf(list.get(i).substring(0, 1));
            String substrSub = list.get(i).substring(2);
            if (map.containsKey(substrSub)) {
                map.get(substrSub).add(substrMark);
            } else {
                map.put(substrSub, new ArrayList<>(List.of(substrMark)));
            }
        }

        return map;
    }

    @Override
    public void deleteMark(Integer id) {
        Optional<Marks> optionalMark = markRepository.findById(id);
        if (optionalMark.isPresent()) {
            markRepository.deleteById(id);
        } else {
            throw new MarkNotFoundException("Mark not found");
        }
    }
}
