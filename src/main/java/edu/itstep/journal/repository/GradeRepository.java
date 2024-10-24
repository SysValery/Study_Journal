package edu.itstep.journal.repository;

import edu.itstep.journal.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

    void deleteById(Long id);



    // Метод для пошуку оцінок за датою
    //List<Grade> findByGradeDate(LocalDate gradeDate);

}
