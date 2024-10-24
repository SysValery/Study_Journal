package edu.itstep.journal.dto;

import java.util.List;

public class StudentWithGradesDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private List<GradeDTO> grades;

    public StudentWithGradesDTO() {}

    public StudentWithGradesDTO(List<GradeDTO> grades, String username, String lastName, String firstName) {
        this.grades = grades;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public StudentWithGradesDTO(int id, String firstName, String lastName, String username, List<GradeDTO> grades) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.grades = grades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<GradeDTO> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeDTO> grades) {
        this.grades = grades;
    }





    @Override
    public String toString() {
        return "StudentWithGradesDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", grades=" + grades +
                '}';
    }
}
