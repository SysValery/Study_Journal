package edu.itstep.journal.repository;

import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT s.id FROM Teacher s JOIN s.user u WHERE u.username = :username")
    Long getTeacherIdByUsername(@Param("username") String username);

    @Query("SELECT g FROM Grade g WHERE g.teacher.id = :teacherId " +
            "AND (:subjectId IS NULL OR g.subject.id = :subjectId) " +
            "AND (:startDate IS NULL OR g.date >= :startDate) " +
            "AND (:endDate IS NULL OR g.date <= :endDate)")
    List<Grade> findFilteredGrades(@Param("teacherId") Long teacherId,
                                   @Param("subjectId") Long subjectId,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);
}

