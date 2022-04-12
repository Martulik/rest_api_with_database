package main.service;

import main.entity.Subjects;
import main.exception.SubjectNotFoundException;
import main.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subjects> listSubjects() {
        List<Subjects> sub = (List<Subjects>) subjectRepository.findAll();
        if (sub.isEmpty()) {
            throw new SubjectNotFoundException("Subjects not found");
        }
        return sub;
    }

    @Override
    public Subjects findSubject(Integer id) {
        Optional<Subjects> optionalSubjects = subjectRepository.findById(id);
        if (optionalSubjects.isPresent()) {
            return optionalSubjects.get();
        } else {
            throw new SubjectNotFoundException("Subject not found");
        }
    }

    @Override
    public Subjects addSubject(Subjects subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Integer id) {
        Optional<Subjects> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            subjectRepository.deleteById(id);
        } else {
            throw new SubjectNotFoundException("Subject not found");
        }
    }
}
