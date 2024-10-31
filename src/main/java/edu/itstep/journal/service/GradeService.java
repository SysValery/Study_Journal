package edu.itstep.journal.service;

import edu.itstep.journal.entity.Grade;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GradeService {

    // Метод для отримання оцінки за ID
    Grade getGradeById(Long id);

    // Метод для створення або оновлення оцінки
    void saveOrUpdateGrade(Grade grade);

    // Метод для видалення оцінки за ID
    void deleteGradeById(Long id);

    // Метод для отримання всіх оцінок
    List<Grade> getAllGrades();

    public List<Grade> getFilteredGrades(Long teacherId, Long subjectId, String startDate, String endDate);

    // Метод для пошуку оцінок за ідентифікатором студента
    @Transactional
    List<Grade> findByStudentId(Long studentId);

    // Метод для пошуку оцінок за ідентифікатором викладача
    @Transactional
    List<Grade> findByTeacherId(Long teacherId);

    // Метод для пошуку оцінок за ідентифікатором предмета
    @Transactional
    List<Grade> findBySubjectId(Long subjectId);
}
