package edu.itstep.journal.repository;

import edu.itstep.journal.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    // Метод для пошуку викладача за ім'ям
    List<Teacher> findByFirstName(String firstName);

    // Метод для пошуку викладача за прізвищем
    List<Teacher> findByLastName(String lastName);

    // Метод для пошуку викладача за ім'ям і прізвищем
    List<Teacher> findByFirstNameAndLastName(String firstName, String lastName);

    // Метод для пошуку викладача за username
    //Teacher findByUsername(String username);

    @Query("SELECT s.id FROM Teacher s JOIN s.user u WHERE u.username = :username")
    Long getTeacherIdByUsername(@Param("username") String username);

    // Метод для отримання всіх викладачів з оцінками, використовуючи JOIN FETCH
    @Query("SELECT t FROM Teacher t JOIN FETCH t.grades")
    List<Teacher> findAllWithGrades();

    @Query("SELECT t FROM Teacher t JOIN FETCH t.grades g JOIN FETCH g.student s JOIN FETCH g.subject WHERE t.id = :teacherId")
    Teacher findByIdWithGrades(@Param("teacherId") Long teacherId);
}
