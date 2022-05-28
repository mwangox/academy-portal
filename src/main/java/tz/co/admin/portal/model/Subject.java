package tz.co.admin.portal.model;

import javax.persistence.*;

@Entity
@Table(name = "academy_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String name) {
        this.subjectName = name;
    }
}
