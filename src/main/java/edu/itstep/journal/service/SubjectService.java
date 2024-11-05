package edu.itstep.journal.service;

import edu.itstep.journal.entity.Grade;
import edu.itstep.journal.entity.Student;
import edu.itstep.journal.entity.Subject;

import java.util.List;

public interface SubjectService {

    // Метод для отримання всіх предметів
    List<Subject> getAllSubjects();
}
