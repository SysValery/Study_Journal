package edu.itstep.journal.controller;

import edu.itstep.journal.entity.Teacher;
import edu.itstep.journal.entity.Subject;
import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.service.StudentService;
import edu.itstep.journal.service.TeacherService;
import edu.itstep.journal.service.SubjectService;
import edu.itstep.journal.service.GradeService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final GradeService gradeService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public TeacherController(GradeService gradeService,
                             TeacherService teacherService,
                             StudentService studentService,
                             SubjectService subjectService) {
        this.gradeService = gradeService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public String showTeacherDetails(@PathVariable Long id,
                                     @RequestParam(required = false) Long subject,
                                     @RequestParam(required = false) String startDate,
                                     @RequestParam(required = false) String endDate,
                                     Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Grade> grades = teacherService.getFilteredGrades(teacher.getId(), subject, startDate, endDate);

        model.addAttribute("teacher", teacher);
        model.addAttribute("grades", grades);
        model.addAttribute("subjects", subjects);

        return "teacher-detail";
    }

    @GetMapping("/newGrade")
    public String showCreateGradeForm(@RequestParam Long teacherId, Model model) {
        Grade grade = new Grade();
        grade.setTeacher(teacherService.getTeacherById(teacherId));
        prepareGradeFormModel(model, grade);
        return "grade-form";
    }

    @GetMapping("/editGrade/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Grade grade = gradeService.getGradeById(id); // Завантажуємо існуючу оцінку з бази даних
        prepareGradeFormModel(model, grade); // Додаємо до моделі об'єкт оцінки і пов'язані дані
        return "grade-form";
    }

    @PostMapping("/saveOrUpdateGrade")
    public String saveOrUpdateGrade(@Valid @ModelAttribute("grade") Grade grade, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            prepareGradeFormModel(model, grade);
            return "grade-form";
        }
        gradeService.saveOrUpdateGrade(grade);
        return "redirect:/teachers/" + grade.getTeacher().getId();
    }

    @PostMapping("/deleteGrade/{id}")
    public String deleteGrade(@PathVariable Long id) {
        Long teacherId = teacherService.getTeacherIdFromGrade(id);
        gradeService.deleteGradeById(id);
        return "redirect:/teachers/" + teacherId;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.trim().isEmpty()) {
                    setValue(null);
                } else {
                    setValue(LocalDate.parse(text));
                }
            }
        });
    }

    // Метод для заповнення моделі спільними атрибутами
    private void prepareGradeFormModel(Model model, Grade grade) {
        model.addAttribute("grade", grade);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("teacherId", grade.getTeacher().getId());
    }
}
