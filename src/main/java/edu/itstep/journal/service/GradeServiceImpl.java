package edu.itstep.journal.service;

import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    @Transactional
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    @Transactional
    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveOrUpdateGrade(Grade grade) {
        gradeRepository.save(grade);
    }

//    @Override
//    @Transactional
//    public void deleteGradeById(Long id) {
//        gradeRepository.deleteGradeById(id);
//    }

    @Override
    @Transactional
    public void deleteGradeById(Long id) {
        gradeRepository.deleteByIdWithQuery(id);
    }

    @Transactional
    @Override
    public List<Grade> findByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Transactional
    @Override
    public List<Grade> findByTeacherId(Long teacherId) {
        return gradeRepository.findByTeacherId(teacherId);
    }

    @Transactional
    @Override
    public List<Grade> findBySubjectId(Long subjectId) {
        return gradeRepository.findBySubjectId(subjectId);
    }

    @Transactional
    @Override
    public List<Grade> getFilteredGrades(Long teacherId, Long subjectId, String startDate, String endDate) {
        // Якщо startDate порожній, встановлюємо його на 1 січня 2000 року
        LocalDate start = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate) : LocalDate.of(2000, 1, 1);

        // Якщо endDate порожній, встановлюємо його на сьогоднішню дату
        LocalDate end = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate) : LocalDate.now();

        return gradeRepository.findFilteredGrades(teacherId, subjectId, start, end);
    }

}
