package Negocios;

import java.io.Serializable;

public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;
    private String lessonID;
    private Subject subject;
    private Teacher teacher;
    private Career career;
    private String hour;

    public Lesson() {}

    public Lesson(String lessonID, Subject subject, Teacher teacher, Career career, String hour) {
        this.lessonID = lessonID;
        this.subject = subject;
        this.teacher = teacher;
        this.career = career;
        this.hour = hour;
    }

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
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

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
