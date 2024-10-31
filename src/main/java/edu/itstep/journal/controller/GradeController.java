package edu.itstep.journal.controller;

import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.service.GradeService;
import edu.itstep.journal.service.StudentService;
import edu.itstep.journal.service.SubjectService;
import edu.itstep.journal.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public GradeController(GradeService gradeService, TeacherService teacherService, StudentService studentService, SubjectService subjectService) {
        this.gradeService = gradeService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    // Відображення форми редагування оцінки
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Grade grade = gradeService.getGradeById(id);
        model.addAttribute("grade", grade);
        return "edit-grade";
    }

    @PostMapping("/delete/{id}")
    public String deleteGrade(@PathVariable("id") Long id) {
        Grade grade = gradeService.getGradeById(id);
        Long teacherId = grade.getTeacher().getId(); // Отримуємо ID викладача для перенаправлення
        System.out.println("Befor deleting");
        System.out.println("Deleting grade with ID: " + id);
        gradeService.deleteGradeById(id); // Видаляємо оцінку через сервіс
        System.out.println("After deleting");
        return "redirect:/teachers/" + teacherId; // Повертає на сторінку викладача
    }

    // Обробка збереження змін після редагування
    @PostMapping("/update")
    public String updateGrade(@ModelAttribute("grade") Grade grade) {
        if (grade.getDate() == null) {
            grade.setDate(LocalDate.now());
        }
        // Перевірка, чи встановлений Teacher для Grade
        if (grade.getTeacher() == null) {
            throw new IllegalArgumentException("Teacher information is missing for the grade.");
        }
        gradeService.saveOrUpdateGrade(grade);
        Long teacherId = grade.getTeacher().getId();

        return "redirect:/teachers/" + teacherId;
    }

    @GetMapping("/new")
    public String showCreateGradeForm(@RequestParam("teacherId") Long teacherId, Model model) {
        Grade grade = new Grade();

        grade.setTeacher(teacherService.getTeacherById(teacherId)); // Встановлюємо викладача
        model.addAttribute("grade", grade);
        model.addAttribute("students", studentService.getAllStudents()); // Отримуємо всіх студентів для вибору
        model.addAttribute("subjects", subjectService.getAllSubjects()); // Отримуємо всі предмети для вибору
        model.addAttribute("teacherId", teacherId);
        return "create-grade";
    }

    @PostMapping("/save")
    public String saveGrade(@ModelAttribute("grade") Grade grade) {
        gradeService.saveOrUpdateGrade(grade);
        Long teacherId = grade.getTeacher().getId();
        return "redirect:/teachers/" + teacherId;
    }
}
