package tz.co.admin.portal.model;

import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;

@Entity
@Table(name = "academy_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    @OneToOne
    private Class studentClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String name) {
        this.studentName = name;
    }

    public Class getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Class studentClass) {
        this.studentClass = studentClass;
    }
}
