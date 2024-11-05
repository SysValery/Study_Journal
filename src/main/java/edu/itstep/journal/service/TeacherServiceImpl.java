package edu.itstep.journal.service;

import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.entity.Teacher;
import edu.itstep.journal.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final GradeService gradeService;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, GradeService gradeService) {
        this.teacherRepository = teacherRepository;
        this.gradeService = gradeService;
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Long getTeacherIdFromGrade(Long gradeId) {
        Grade grade = gradeService.getGradeById(gradeId);
        if (grade != null && grade.getTeacher() != null) {
            return grade.getTeacher().getId();
        }
        throw new IllegalArgumentException("Teacher information is missing for the grade.");
    }

    @Override
    public void verifyTeacherExists(Grade grade) {
        if (grade.getTeacher() == null) {
            throw new IllegalArgumentException("Teacher information is missing for the grade.");
        }
    }

    @Override
    public List<Grade> getFilteredGrades(Long teacherId, Long subjectId, String startDate, String endDate) {
        // Якщо startDate порожній, встановлюємо його на 1 січня 2000 року
        LocalDate start = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate) : LocalDate.of(2000, 1, 1);

        // Якщо endDate порожній, встановлюємо його на сьогоднішню дату
        LocalDate end = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate) : LocalDate.now();

        return teacherRepository.findFilteredGrades(teacherId, subjectId, start, end);
    }
}
