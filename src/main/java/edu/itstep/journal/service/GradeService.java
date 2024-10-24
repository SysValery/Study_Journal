package edu.itstep.journal.service;

import edu.itstep.journal.entity.Grade;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface GradeService {

    // Метод для отримання всіх оцінок
    List<Grade> getAllGrades();

    // Метод для отримання оцінки за ID
    Grade getGradeById(Long id);


    // Метод для створення або оновлення оцінки
    void saveOrUpdateGrade(Grade grade);

    // Метод для видалення оцінки за ID
    void deleteGradeById(Integer id);

    // Метод для пошуку оцінок за ідентифікатором студента
    List<Grade> findByStudentId(Integer studentId);

    // Метод для пошуку оцінок за ідентифікатором викладача
    List<Grade> findByTeacherId(Integer teacherId);

    // Метод для пошуку оцінок за ідентифікатором предмета
    List<Grade> findBySubjectId(Integer subjectId);

    @Transactional
    void deleteGradeById(Long id);

    @Transactional
    List<Grade> findByStudentId(Long studentId);

    @Transactional
    List<Grade> findByTeacherId(Long teacherId);

    @Transactional
    List<Grade> findBySubjectId(Long subjectId);

//    //@Transactional
//    List<Grade> findByGradeDate(LocalDate date);
//
//    // Метод для пошуку оцінок за датою
//    List<Grade> findByGradeDate(String date);
}
