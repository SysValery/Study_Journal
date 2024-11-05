package edu.itstep.journal.service;

import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveOrUpdateGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    @Override
    @Transactional
    public void deleteGradeById(Long id) {
        gradeRepository.deleteByIdWithQuery(id);
    }
}

