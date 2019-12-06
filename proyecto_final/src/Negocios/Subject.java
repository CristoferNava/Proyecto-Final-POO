package Negocios;

import java.io.Serializable;

public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    private String subjectID;
    private String name;
    private Teacher teacher;
    private Career career;
    private Department department;

    public Subject() {}

    public Subject(String subjectID, String name, Teacher teacher, Career career) {
        this.subjectID = subjectID;
        this.name = name;
        this.teacher = teacher;
        this.career = career;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
