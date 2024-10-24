package edu.itstep.journal.dto;

public class GradeDTO {
    private int grade;
    private String comment;

    public GradeDTO() {
    }

    public GradeDTO(int grade, String comment) {
        this.grade = grade;
        this.comment = comment;
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

    @Override
    public String toString() {
        return "GradeDTO{" +
                "grade=" + grade +
                ", comment='" + comment + '\'' +
                '}';
    }
}

