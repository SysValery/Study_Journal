package edu.itstep.journal.controller;

import edu.itstep.journal.service.StudentService;
import edu.itstep.journal.service.SubjectService;
import edu.itstep.journal.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    // Відображення головної сторінки адміністратора
    @GetMapping("/home")
    public String adminHome() {
        return "admin/home";
    }

    // Переходи до відповідних сторінок
    @GetMapping("/students")
    public String manageStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "admin/students";
    }

    @GetMapping("/teachers")
    public String manageTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "admin/teachers";
    }

    @GetMapping("/subjects")
    public String manageSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "admin/subjects";
    }
}