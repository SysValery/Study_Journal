package edu.itstep.journal.entity;

//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
import javax.validation.*;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Min(value = 0, message = "Оцінка має бути більша за 0")
    @Max(value = 100, message = "Оцінка має бути менша або рівна 100")
    @Column(name = "grade")
    private int grade;

    @NotNull(message = "Comment is required")
    @NotBlank(message = "Поле коментар не повинно бути порожнім!!")
    @Column(name = "comment")
    private String comment;

    @NotNull(message = "Дата є обов'язковою")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Дата не може бути у майбутньому")
    //@MinDate("2020-01-01")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    public Grade() {
    }

    public Grade(int grade, String comment, LocalDate date) {
        this.grade = grade;
        this.comment = comment;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", student=" + student +
                ", subject=" + subject +
                ", teacher=" + teacher +
                '}';
    }
}
