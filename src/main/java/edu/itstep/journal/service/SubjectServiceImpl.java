package edu.itstep.journal.service;

import edu.itstep.journal.entity.Subject;
import edu.itstep.journal.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    @Override
    @Transactional
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    @Transactional
    public Subject getSubjectById(Integer id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveOrUpdateSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    @Transactional
    public void deleteSubjectById(Integer id) {
        subjectRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }
}
