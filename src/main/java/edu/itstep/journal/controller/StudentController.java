package edu.itstep.journal.controller;
import edu.itstep.journal.dto.StudentWithGradesDTO;
import edu.itstep.journal.entity.Student;
import edu.itstep.journal.repository.StudentRepository;
import edu.itstep.journal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    // Отримати всіх студентів
    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

     @GetMapping("/{id}")
    public String getStudentById(@PathVariable Long id,
                                     @RequestParam(required = false, defaultValue = "subject") String sortField,
                                     @RequestParam(required = false, defaultValue = "asc") String sortDir,
                                     Model model) {

        Student student = studentService.getStudentById(id, sortField, sortDir);
        model.addAttribute("student", student);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        return "student-detail";
    }


    // Створення або оновлення студента
    @PostMapping("/save")
    public String saveOrUpdateStudent(@ModelAttribute("student") Student student) {
        studentService.saveOrUpdateStudent(student);
        return "redirect:/students";
    }

    // Видалення студента за ID
    @GetMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

}
