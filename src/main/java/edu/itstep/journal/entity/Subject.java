package edu.itstep.journal.entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Назва предмету обов'язкова")
    @Column(name = "name")
    private String name;

    public Subject(String name) {
        this.name = name;
    }

    public Subject() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
