package edu.itstep.journal.service;

import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.entity.Student;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface StudentService {

    // Метод для отримання всіх студентів
    List<Student> getAllStudents();

    Student getStudentById(Long id);

    List<Grade> getFilteredOrAllGradesForStudents(
            Student student, Long subjectId, String startDate, String endDate);
}
