package edu.itstep.journal.service;

import edu.itstep.journal.entity.Grade;

public interface GradeService {

    // Метод для отримання оцінки за ID
    Grade getGradeById(Long id);

    // Метод для створення або оновлення оцінки
    void saveOrUpdateGrade(Grade grade);

    // Метод для видалення оцінки за ID
    void deleteGradeById(Long id);
}

