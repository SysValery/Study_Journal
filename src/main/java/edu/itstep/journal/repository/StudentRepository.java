package edu.itstep.journal.repository;
import edu.itstep.journal.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.itstep.journal.entity.Student;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s.id FROM Student s JOIN s.user u WHERE u.username = :username")
    Long getStudentIdByUsername(@Param("username") String username);

    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId " +
            "AND (:subjectId IS NULL OR g.subject.id = :subjectId) " +
            "AND g.date >= :startDate " +
            "AND g.date <= :endDate")
    List<Grade> findFilteredGradesForStudents(@Param("studentId") Long studentId,
                                              @Param("subjectId") Long subjectId,
                                              @Param("startDate") LocalDate startDate,
                                              @Param("endDate") LocalDate endDate);

}

