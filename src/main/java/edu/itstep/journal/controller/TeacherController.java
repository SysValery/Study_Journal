package edu.itstep.journal.controller;

import edu.itstep.journal.entity.Teacher;
import edu.itstep.journal.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
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
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacher-detail";
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
