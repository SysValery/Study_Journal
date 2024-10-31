package edu.itstep.journal.controller;

import edu.itstep.journal.entity.Teacher;
import edu.itstep.journal.entity.Subject;
import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.service.TeacherService;
import edu.itstep.journal.service.SubjectService;
import edu.itstep.journal.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final SubjectService subjectService;  // Додано
    private final GradeService gradeService;

    @Autowired
    public TeacherController(TeacherService teacherService, SubjectService subjectService, GradeService gradeService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.gradeService = gradeService;
    }

    // Отримати всіх викладачів
    @GetMapping
    public String getAllTeachers(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        System.out.println(teachers);
        model.addAttribute("teachers", teachers);
        return "teachers";
    }

    // Отримати викладача за ID
     @GetMapping("/{id}")
    public String getTeacherById(@PathVariable("id") Long id, Model model) {
//        Teacher teacher = teacherService.getTeacherById(id);
//        model.addAttribute("teacher", teacher);
//        return "teacher-detail";
         return "redirect:/teachers/" + id + "/details";
        }


    @GetMapping("/{id}/details")
    public String showTeacherDetails(
            @PathVariable Long id,
            @RequestParam(required = false) Long subject,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Model model) {

        Teacher teacher = teacherService.getTeacherById(id);
        List<Subject> subjects = subjectService.getAllSubjects(); // Отримуємо список предметів
        List<Grade> grades;

        if (subject != null || (startDate != null && endDate != null)) {
            grades = gradeService.getFilteredGrades(teacher.getId(), subject, startDate, endDate);
        } else {
            grades = teacher.getGrades();
        }

        model.addAttribute("teacher", teacher);
        model.addAttribute("grades", grades);
        model.addAttribute("subjects", subjects); // Передаємо список предметів в модель

        return "teacher-detail";  // Назва JSP-сторінки
    }

    // Відображення форми для створення нового викладача
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-form";
    }

    // Створення або оновлення викладача
    @PostMapping("/save")
    public String saveOrUpdateTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.saveOrUpdateTeacher(teacher);
        return "redirect:/teachers";
    }

    // Відображення форми для редагування викладача
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacher-form";
    }

    // Видалення викладача за ID
    @GetMapping("/delete/{id}")
    public String deleteTeacherById(@PathVariable("id") Long id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/teachers";
    }
}
