package edu.itstep.journal.repository;

import edu.itstep.journal.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    // Метод для пошуку оцінок за ідентифікатором студента
    List<Grade> findByStudentId(Long studentId);

    // Метод для пошуку оцінок за ідентифікатором викладача
    List<Grade> findByTeacherId(Long teacherId);

    // Метод для пошуку оцінок за ідентифікатором предмета
    List<Grade> findBySubjectId(Long subjectId);

    void deleteGradeById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Grade g WHERE g.id = :id")
    void deleteByIdWithQuery(@Param("id") Long id);

    @Query("SELECT g FROM Grade g WHERE g.teacher.id = :teacherId " +
            "AND (:subjectId IS NULL OR g.subject.id = :subjectId) " +
            "AND (:startDate IS NULL OR g.date >= :startDate) " +
            "AND (:endDate IS NULL OR g.date <= :endDate)")
    List<Grade> findFilteredGrades(@Param("teacherId") Long teacherId,
                                   @Param("subjectId") Long subjectId,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);
}
