package edu.itstep.journal.service;

import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.entity.Teacher;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherService {

    // Метод для отримання викладача за ID
    Teacher getTeacherById(Long id);

    // Метод для отримання ID викладача з оцінки
    Long getTeacherIdFromGrade(Long gradeId);

    // Метод для перевірки, чи встановлено викладача для оцінки
    void verifyTeacherExists(Grade grade);

    //метод отримання фільтрованого списку
    List<Grade> getFilteredGrades(Long teacherId, Long subjectId, String startDate, String endDate);

}
