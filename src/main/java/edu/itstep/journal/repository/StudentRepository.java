package edu.itstep.journal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.itstep.journal.entity.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    // Метод для пошуку студентів за іменем
    List<Student> findByFirstName(String firstName);

    // Метод для пошуку студентів за прізвищем
    List<Student> findByLastName(String lastName);

    // Метод для пошуку студентів за іменем і прізвищем
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT s.id FROM Student s JOIN s.user u WHERE u.username = :username")
    Long getStudentIdByUsername(@Param("username") String username);

    // Метод для отримання всіх студентів з оцінками
    @Query("SELECT s FROM Student s JOIN FETCH s.grades")
    List<Student> findAllWithGrades();

}