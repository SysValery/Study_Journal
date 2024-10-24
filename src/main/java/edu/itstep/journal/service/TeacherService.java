package edu.itstep.journal.service;

import edu.itstep.journal.entity.Teacher;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherService {

    Teacher getTeacherByIdWithGrades(Long id);

    // Метод для отримання всіх викладачів
    List<Teacher> getAllTeachers();

    // Метод для отримання викладача за ID
    @Transactional
    Teacher getTeacherById(Long id);

    // Метод для створення або оновлення викладача
    void saveOrUpdateTeacher(Teacher teacher);

    // Метод для видалення викладача за ID

    void deleteTeacherById(Long id);

    Long getTecherIdByUsername(String username);

    // Метод для пошуку викладачів за ім'ям
    List<Teacher> findByFirstName(String firstName);

    // Метод для пошуку викладачів за прізвищем
    List<Teacher> findByLastName(String lastName);

    // Метод для пошуку викладача за ім'ям та прізвищем
    List<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
}
