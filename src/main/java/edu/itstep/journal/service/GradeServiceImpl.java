package edu.itstep.journal.service;

import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void deleteGradeById(Integer id) {

    }

    @Override
    public List<Grade> findByStudentId(Integer studentId) {
        return List.of();
    }

    @Override
    public List<Grade> findByTeacherId(Integer teacherId) {
        return List.of();
    }

    @Override
    public List<Grade> findBySubjectId(Integer subjectId) {
        return List.of();
    }


    @Transactional
    @Override
    public void deleteGradeById(Long id) {
        gradeRepository.deleteById(id);
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

//    @Override
//    @Transactional
//    public List<Grade> findByGradeDate(LocalDate date) {
//        return gradeRepository.findByGradeDate(date);
//    }
//
//    @Override
//    @Transactional
//    public List<Grade> findByGradeDate(String dateStr) {
//        try {
//            LocalDate date = LocalDate.parse(dateStr);
//            return gradeRepository.findByGradeDate(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>(); // Повертаємо порожній список у разі помилки
//        }
//    }
}
