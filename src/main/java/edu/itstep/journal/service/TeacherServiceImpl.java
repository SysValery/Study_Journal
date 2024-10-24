package edu.itstep.journal.service;

import edu.itstep.journal.entity.Teacher;
import edu.itstep.journal.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    @Transactional
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveOrUpdateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }


    @Override
    @Transactional
    public Teacher getTeacherByIdWithGrades(Long id) {
        return teacherRepository.findByIdWithGrades(id);
    }

    @Override
    public void deleteTeacherById(Long id) {

    }

    @Override
    public Long getTecherIdByUsername(String username) {
        return teacherRepository.getTeacherIdByUsername(username);
    }


    @Override
    @Transactional
    public List<Teacher> findByFirstName(String firstName) {
        return teacherRepository.findByFirstName(firstName);
    }

    @Override
    @Transactional
    public List<Teacher> findByLastName(String lastName) {
        return teacherRepository.findByLastName(lastName);
    }

    @Override
    @Transactional
    public List<Teacher> findByFirstNameAndLastName(String firstName, String lastName) {
        return teacherRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
