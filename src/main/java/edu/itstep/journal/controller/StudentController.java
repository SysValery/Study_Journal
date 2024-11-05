package edu.itstep.journal.controller;
import edu.itstep.journal.entity.Student;
import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.service.StudentService;
import edu.itstep.journal.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public StudentController(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public String getStudentDetails(@PathVariable Long id,
                                    @RequestParam(required = false) Long subject,
                                    @RequestParam(required = false) String startDate,
                                    @RequestParam(required = false) String endDate,
                                    Model model) {

        Student student = studentService.getStudentById(id);
        List<Grade> grades = studentService.getFilteredOrAllGradesForStudents(student, subject, startDate, endDate);

        model.addAttribute("student", student);
        model.addAttribute("grades", grades);
        model.addAttribute("subjects", subjectService.getAllSubjects());

        return "student-detail";
    }

}
