package edu.itstep.journal.service;

import edu.itstep.journal.entity.Subject;

import java.util.List;

public interface SubjectService {

    // Метод для отримання всіх предметів
    List<Subject> getAllSubjects();

    // Метод для отримання предмета за ID
    Subject getSubjectById(Integer id);

    // Метод для створення або оновлення предмета
    void saveOrUpdateSubject(Subject subject);

    // Метод для видалення предмета за ID
    void deleteSubjectById(Integer id);

    // Метод для пошуку предметів за назвою
    List<Subject> findByName(String name);
}
