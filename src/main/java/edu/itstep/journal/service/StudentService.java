package edu.itstep.journal.service;

import edu.itstep.journal.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {

    // Метод для отримання всіх студентів
    List<Student> getAllStudents();

    // Метод для отримання студента за ID
    Student getStudentById(Integer id);

    @Transactional
    Student getStudentById(Long id);

    // Метод для створення або оновлення студента
    void saveOrUpdateStudent(Student student);

    // Метод для видалення студента за ID
    void deleteStudentById(Integer id);

    @Transactional
    void deleteStudentById(Long id);

    Long getStudentIdByUsername(String username);

    // Метод для пошуку студента за username
    Student findByUsername(String username);

    // Метод для пошуку студентів за іменем
    List<Student> findByFirstName(String firstName);

    // Метод для пошуку студентів за прізвищем
    List<Student> findByLastName(String lastName);

    // Метод для пошуку студента за іменем та прізвищем
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
}
